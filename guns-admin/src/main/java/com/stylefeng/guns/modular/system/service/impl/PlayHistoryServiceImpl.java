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
import org.apache.commons.collections.ListUtils;
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
    //视频加入用户收藏夹
    public Result addVideoToFav(List<Integer> vids, String uuidToken){
        if (CollectionUtils.isEmpty(vids)){
            return Result.createByErrorMessage("请重新同步播放历史");
        }
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }

        //查找该用户的所有播放历史
        List<Integer> existVids = playHistoryMapper.selectVideoIdsByMember(member.getId());
        //得到前台给的  还没有添加到数据库的播放视频记录
        List<Integer> subtract = ListUtils.subtract(vids, existVids);

        if (CollectionUtils.isEmpty(subtract)){
            return Result.createBySuccessMessage("同步成功");
        }
        //List<Data> videoList = dataService.selectVideosByIds(subtract);
        //得到未添加到数据库的视频信息
        List<Data> videoList = dataService.selectBatchVideoIds(subtract);

        //构建插入的播放记录list
        List<PlayHistory> playHistoryList = Lists.newArrayList();
        for (Data data:videoList) {
            PlayHistory playHistory = assemPlayHistoryFromVideoAndMember(member, data);
            playHistoryList.add(playHistory);
        }

        boolean isInsert = this.insertBatch(playHistoryList);
        if (isInsert){
            PlayHistoryVo playHistoryVo = getFavortieVoLimit(uuidToken);
            return Result.createBySuccess("同步成功",playHistoryVo);
        }
        return Result.createByErrorMessage("同步失败");
    }


    //装配新增的favorite对象
    private PlayHistory assemPlayHistoryFromVideoAndMember(Member member,Data video){
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

        playHistory.setTypeId(video.getTid());
        playHistory.setVideoDirector(video.getvDirector());
        playHistory.setVideoPublisharea(video.getvPublisharea());
        playHistory.setVideoPublishyear(video.getvPublishyear());

        return playHistory;
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
