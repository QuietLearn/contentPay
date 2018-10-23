package com.stylefeng.guns.modular.system.service.impl;

import com.alipay.api.domain.ProductVOOptions;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Result addVideoToFav(int vid, String uuidToken){
        Data video = dataService.getDataByVid(vid);
        if (video==null){
            return Result.createByErrorMessage("该视频不存在");
        }
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);
        if(member==null){
            return Result.createByErrorMessage("请重新登录");
        }
        EntityWrapper<Favorite> wrapper=new EntityWrapper<>();
        wrapper.eq("member_id",member.getId());
        wrapper.eq("video_id",video.getvId());
        Integer resultCount = favoriteMapper.selectCount(wrapper);
        if (resultCount >0){
            Integer deleteCount = favoriteMapper.delete(wrapper);
            if (deleteCount>0)
                return Result.createBySuccessMessage("该视频移出收藏");
            return Result.createByErrorMessage("移出收藏失败");
        }
        //进行新增收藏夹操作
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
        Integer insertCount = favoriteMapper.insert(favorite);
        if (insertCount>0){
            return Result.createBySuccessMessage("添加成功");
        }


        return Result.createByErrorMessage("添加失败");
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
