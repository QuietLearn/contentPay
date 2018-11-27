package com.stylefeng.guns.modular.system.service.impl;

import com.google.common.collect.Lists;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.system.dao.FavoriteMapper;
import com.stylefeng.guns.modular.system.dao.MemberMapper;
import com.stylefeng.guns.modular.system.model.Member;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hyj on 2018/10/24
 */
/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class FavoriteServiceImplTest {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Test
    public void addVideoToFav() {
        String uuidToken = "276517ae0d7f48688e93fd0734d5fd27";
        List<Integer> vids = Lists.newArrayList();
        Integer i = 7000;
        vids.add(i);
        Member member = memberMapper.selectMemberByUuidToken(uuidToken);


        favoriteMapper.deleteVideoIdsByMember(member.getId(),vids);
    }

    @Test
    public void test1(){
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        List intersection = ListUtils.intersection(list1, list2);
        System.out.println(intersection);

        List subtract = ListUtils.subtract(list1, list2);
        System.out.println(subtract);
    }

    @Test
    public void fun3(){
        File file = new File("G:\\英雄时刻\\784510436\\hero.avi");
        System.out.println(file.toString());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.getAbsolutePath());

    }
}