package com.stylefeng.guns.modular.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.dao.RelationMapper;
import com.stylefeng.guns.modular.system.model.Relation;
import com.stylefeng.guns.modular.system.model.Video;
import com.stylefeng.guns.modular.system.service.IVideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by hyj on 2018/10/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {

    @Autowired
    private RelationMapper relationMapper;

    @Test
    public void fun1(){

        Relation relation = new Relation();
        relation.setMenuid(1l);
        relation.setRoleid(1);

        relationMapper.insert(relation);
        System.out.println(relation.getId());
    }
}