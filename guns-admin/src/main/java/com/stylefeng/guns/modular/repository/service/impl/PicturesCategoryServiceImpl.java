package com.stylefeng.guns.modular.repository.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.stylefeng.guns.modular.system.model.Data;
import com.stylefeng.guns.modular.system.model.PicturesCategory;
import com.stylefeng.guns.modular.system.dao.PicturesCategoryMapper;
import com.stylefeng.guns.modular.repository.service.IPicturesCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.service.IVideoService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 图集资源库表 服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-11-19
 */
@Service
public class PicturesCategoryServiceImpl extends ServiceImpl<PicturesCategoryMapper, PicturesCategory> implements IPicturesCategoryService {
    @Autowired
    private PicturesCategoryMapper picturesCategoryMapper;

    @Autowired
    private IVideoService iVideoService;

    public String syncVideo(){
        return "同步视频数据成功";
    }
}
