package com.stylefeng.guns.db;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.modular.system.dao.OperationLogMapper;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.service.IOperationLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by hyj on 2018/11/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReadLogTest {

    @Autowired
    private IOperationLogService operationLogService;

    @Test
    public void fun1(){
        Wrapper<OperationLog> wrapper = new EntityWrapper<>();
        wrapper.setSqlSelect("message");
        wrapper.eq("id",790);
        String message = (String )operationLogService.selectObj(wrapper);
        System.out.println(message);
    }
}
