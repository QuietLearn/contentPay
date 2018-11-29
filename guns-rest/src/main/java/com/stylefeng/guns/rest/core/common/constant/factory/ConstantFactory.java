package com.stylefeng.guns.rest.core.common.constant.factory;

import com.stylefeng.guns.core.util.SpringContextHolder;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    //private IVideoSourceService videoSourceService = SpringContextHolder.getBean(IVideoSourceService.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }



  /*  public String getVideoSourceNameById(Integer videoSourceId){
        VideoSource videoSource = videoSourceService.selectById(videoSourceId);
        return videoSource.getName();
    }*/
}
