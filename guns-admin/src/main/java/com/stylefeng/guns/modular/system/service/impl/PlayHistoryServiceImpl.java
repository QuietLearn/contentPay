package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.FavoriteMapper;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.model.Favorite;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.model.PlayHistory;
import com.stylefeng.guns.modular.system.dao.PlayHistoryMapper;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.stylefeng.guns.modular.system.service.IPlayHistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.PlayHistoryVo;
import com.stylefeng.guns.modular.system.vo.VideoVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-23
 */
@Service
public class PlayHistoryServiceImpl extends ServiceImpl<PlayHistoryMapper, PlayHistory> implements IPlayHistoryService {
    @Autowired
    private IDataService dataService;

    @Autowired
    private PlayHistoryMapper playHistoryMapper;

    @Autowired
    private MemberMapper memberMapper;


    //列举用户的收藏夹
    public Result<PlayHistoryVo> list(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        PlayHistoryVo playHistoryVo = getFavortieVoLimit(uuidToken);
        return Result.createBySuccess(playHistoryVo);
    }

    //视频加入用户播放历史
    public Result addVideoToFav(int vid, String uuidToken){
        Data video = dataService.getDataByVid(vid);
        if (video==null){
            return Result.createByErrorMessage("该视频不存在");
        }

        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }

        PlayHistory existPlayHistory = playHistoryMapper.selectByVideoId(vid,member.getId());
        if (existPlayHistory!=null){
            existPlayHistory.setGmtModified(new Date());
            playHistoryMapper.updateById(existPlayHistory);
            return Result.createBySuccessMessage("播放历史更新");
        }

        //进行新增历史记录操作
        PlayHistory playHistory = new PlayHistory();
        playHistory.setGmtCreated(new Date());
        playHistory.setGmtModified(new Date());
        playHistory.setMemberId(member.getId());
        playHistory.setMemberUsername(member.getUsername());
        playHistory.setVideoId(video.getvId());
        playHistory.setVideoName(video.getvName());
        playHistory.setVideoNote(video.getvNote());
        playHistory.setVideoPic(video.getvPic());
        playHistory.setVideoActor(video.getvActor());
        Integer insertCount = playHistoryMapper.insert(playHistory);
        if (insertCount>0){
            return Result.createBySuccessMessage("添加成功");
        }


        return Result.createByErrorMessage("添加失败");
    }



    private PlayHistoryVo getFavortieVoLimit(String uuidToken){
        PlayHistoryVo playHistoryVo = new PlayHistoryVo();
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
//        if(member==null) return null;
        EntityWrapper<PlayHistory> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("member_id",member.getId());
        entityWrapper.orderBy("gmt_modified",false);
        List<PlayHistory> playHistories = playHistoryMapper.selectList(entityWrapper);
        /*playHistories.sort((x,y) -> Date.);*/


        List<VideoVo> videoVoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(playHistories)){
            for (PlayHistory playHistory:playHistories){
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(playHistory,videoVo);
                videoVoList.add(videoVo);
            }
        }

        playHistoryVo.setVideoVoList(videoVoList);
        return playHistoryVo;
    }



}
