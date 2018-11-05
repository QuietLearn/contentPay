package com.stylefeng.guns.modular.system.service.impl;

import com.alipay.api.domain.ProductVOOptions;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.modular.system.dao.DataMapper;
import com.stylefeng.guns.modular.system.dao.MemberLoginLogMapper;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.dao.FavoriteMapper;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.service.ITypeService;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VideoVo;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 收藏夹表 分别查询三个表(先分别查出每个表资源的ids，在用in查询) 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-23
 */
@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements IFavoriteService {
    private Logger logger = LoggerFactory.getLogger(FavoriteServiceImpl.class);
    @Autowired
    private IDataService dataService;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private MemberLoginLogMapper memberLoginLogMapper;

    //后端接口
    public long deletePointList(String ids) {
        String[] ss = ids.split(",");
        long count= 0;
        for (String s : ss) {
            this.deleteById(Integer.parseInt(s));
            count++;
        }
        return count;
    }



    @Override
    //列举用户的收藏夹
    public Result<FavoriteVo> list(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        FavoriteVo favoriteVo = getFavortieVoLimit(uuidToken);
        return Result.createBySuccess(favoriteVo);
    }


    /**
     * //视频加入用户收藏夹 ,String appVer,String channel
     * //如果后面同步收藏夹内容，app 版本要不要覆盖之前的（收藏夹相同不会做处理）
     * @param vids
     * @param uuidToken
     * @return
     */
    @Override
    public Result addVideoToFav(List<Integer> vids, String uuidToken){
        if (CollectionUtils.isEmpty(vids)){
            return Result.createByErrorMessage("请重新同步收藏夹视频");
        }
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }


        Date date = memberLoginLogMapper.selectMaxCreatime(member.getId());

        MemberLoginLog memberLoginLog = memberLoginLogMapper.selectLoginLogByCreateTime(date, member.getId());


        favoriteMapper.deleteVideoIdsByMember(member.getId(),vids);


       /* EntityWrapper<Favorite> wrapper=new EntityWrapper<>();
        wrapper.eq("member_id",member.getId());
        wrapper.notIn("video_id",vids);
        wrapper.setSqlSelect("video_id");*/
        //不在说明数据库是旧的，用户已经取消了收藏

        //wrapper.eq("video_id",video.getvId());

       /* logger.info("videIds有几个:{}",videoIds.size());
        if (CollectionUtils.isNotEmpty(videoIds)){
            favoriteMapper.deleteBatchIds(videoIds);
        }*/

        //查找该用户的所有收藏视频id
        List<Integer> existVids = favoriteMapper.selectVideoIdsByMember(member.getId());

        List<Integer> subtract = ListUtils.subtract(vids, existVids);
        if (CollectionUtils.isEmpty(subtract)){
            return Result.createBySuccessMessage("同步成功");
        }


//        List<Data> videoList = dataService.selectVideosByIds(subtract);
        List<Data> videoList = dataService.selectBatchVideoIds(subtract);

        //构建插入的收藏夹list
        List<Favorite> favoriteList = Lists.newArrayList();
        for (Data data:videoList) {
            Favorite favorite = assemFavoriteFromVideoAndMember(member, data,memberLoginLog);
            favoriteList.add(favorite);
        }

        boolean isInsert = this.insertBatch(favoriteList);
        if (isInsert){
            FavoriteVo favoriteVo = getFavortieVoLimit(uuidToken);

            return Result.createBySuccess("同步成功",favoriteVo);
        }
        return Result.createByErrorMessage("同步失败");
    }


    //装配新增的favorite对象
    private Favorite assemFavoriteFromVideoAndMember(Member member,Data video,MemberLoginLog memberLoginLog){
        Favorite favorite = new Favorite();
        favorite.setGmtCreated(new Date());
        favorite.setGmtModified(new Date());
        favorite.setMemberId(member.getId());
        favorite.setMemberUsername(member.getUsername());
        favorite.setVideoId(video.getvId());
        favorite.setVideoName(video.getvName());
        favorite.setVideoNote(video.getvNote());
        favorite.setVideoPic(video.getvPic());
        favorite.setVideoActor(video.getvActor());

        favorite.setTypeId(video.getTid());
        favorite.setVideoDirector(video.getvDirector());
        favorite.setVideoPublisharea(video.getvPublisharea());
        favorite.setVideoPublishyear(video.getvPublishyear());

        if (memberLoginLog!=null){
            favorite.setAppId(memberLoginLog.getAppId());
            favorite.setAppVer(memberLoginLog.getUpdateAppver());
            favorite.setChannel(memberLoginLog.getChannel());
        }


        return favorite;
    }



    private FavoriteVo getFavortieVoLimit(String uuidToken){
        FavoriteVo favoriteVo = new FavoriteVo();
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
//        if(member==null) return null;
        EntityWrapper<Favorite> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("member_id",member.getId());
        entityWrapper.orderBy("gmt_created",false);
        List<Favorite> favorites = favoriteMapper.selectList(entityWrapper);

        List<VideoVo> videoVoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(favorites)){
            for (Favorite favorite:favorites){

                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(favorite,videoVo);
                videoVoList.add(videoVo);
            }
        }

        favoriteVo.setVideoVoList(videoVoList);
        return favoriteVo;
    }

}
