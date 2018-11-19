package com.stylefeng.guns.modular.repository.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.*;
import com.stylefeng.guns.modular.system.model.AppContent;
import com.stylefeng.guns.modular.repository.service.IAppContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.model.ModelAppSource;
import com.stylefeng.guns.modular.system.model.PhotosAppSource;
import com.stylefeng.guns.modular.system.model.VideoAppSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
@Service
public class AppContentServiceImpl extends ServiceImpl<AppContentMapper, AppContent> implements IAppContentService {
    @Autowired
    private AppContentMapper appContentMapper;

    @Autowired
    private ModelAppSourceMapper modelAppSourceMapper;

    @Autowired
    private VideoAppSourceMapper videoAppSourceMapper;

    @Autowired
    private PhotosAppSourceMapper photosAppSourceMapper;

    @Autowired
    private VideoRepositoryMapper videoRepositoryMapper;

    public Result searchAppContent(String appId, String appVer, String channelId,String type){
        AppContent appContent = appContentMapper.searchAppContent(appId,appVer,channelId);
        Wrapper wrapper;
        List list = Lists.newArrayList();
        if (appContent==null)
            return Result.createByErrorMessage("不存在该应用内容");
        switch (type){
        case "video":
                /*wrapper = new EntityWrapper<VideoAppSource>();
                wrapper.eq("app_content_id",);
                list = videoAppSourceMapper.selectList(wrapper);*/
                list = videoRepositoryMapper.selectListByContentId(appContent.getId());
                break;
        case "photos":
                wrapper = new EntityWrapper<PhotosAppSource>();
                wrapper.eq("app_content_id",appContent.getId());
                list = photosAppSourceMapper.selectList(wrapper);
                break;

        case "model":
                wrapper = new EntityWrapper<ModelAppSource>();
                wrapper.eq("app_content_id",appContent.getId());
                list = modelAppSourceMapper.selectList(wrapper);
                break;

        }

        return Result.createBySuccess(list);
    }
}
