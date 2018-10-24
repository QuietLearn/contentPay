package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.dao.VideoMapper;
import com.stylefeng.guns.modular.system.service.IVideoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hyj
 * @since 2018-10-24
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements IVideoService {

    public Result confirmVideoPrice(int vid){
        Video video = this.selectById(vid);
        if (video.getvMoney()==0){
            return Result.createBySuccessMessage("视频免费,欢迎观看");
        }
        return Result.createByErrorMessage(MessageFormat.format("请付{0}金币观看",video.getvMoney()));
    }
}
