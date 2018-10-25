package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.service.IVideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hyj on 2018/10/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VideoServiceImplTest {

    @Autowired
    private IVideoService videoService;

    @Test
    public void fun1(){

        Wrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("v_id");
        List<Object> objects = videoService.selectObjs(wrapper);
        System.out.println(objects);
    }
}