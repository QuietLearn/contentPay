package com.stylefeng.guns.modular.system.service.impl;

import com.alipay.api.domain.ProductVOOptions;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.DataMapper;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.model.Favorite;
import com.stylefeng.guns.modular.system.dao.FavoriteMapper;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.service.IDataService;
import com.stylefeng.guns.modular.system.service.IFavoriteService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.vo.FavoriteVo;
import com.stylefeng.guns.modular.system.vo.VideoVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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


    //列举用户的收藏夹
    public Result<FavoriteVo> list(String uuidToken){
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        FavoriteVo favoriteVo = getFavortieVoLimit(uuidToken);
        return Result.createBySuccess(favoriteVo);
    }

    //视频加入用户收藏夹
    public Result addVideoToFav(List<Integer> vids, String uuidToken){
        if (CollectionUtils.isEmpty(vids)){
            return Result.createByErrorMessage("请重新同步收藏夹视频");
        }
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }

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
//        ListUtils.

        List<Integer> subtract = ListUtils.subtract(vids, existVids);

//        List<Data> videoList = dataService.selectVideosByIds(subtract);
        List<Data> videoList = dataService.selectBatchVideoIds(subtract);

        //构建插入的收藏夹list
        List<Favorite> favoriteList = Lists.newArrayList();
        for (Data data:videoList) {
            Favorite favorite = assemFavoriteFromVideoAndMember(member, data);
            favoriteList.add(favorite);
        }

        boolean isInsert = this.insertBatch(favoriteList);
        if (isInsert){
            return Result.createBySuccessMessage("同步成功");
        }
        return Result.createByErrorMessage("同步失败");
    }


    //装配新增的favorite对象
    private Favorite assemFavoriteFromVideoAndMember(Member member,Data video){
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
