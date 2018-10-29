package com.stylefeng.guns.core.factory;

import com.stylefeng.guns.core.common.constant.state.AllConst;
import com.stylefeng.guns.modular.system.model.Note;

import java.util.Date;

/**
 * Created by hyj on 2018/10/29
 */
public class AllFactory {

    public static Note createNote(String message, String mobile,Integer appId,String appVer,String channel){
        Note note = new Note();
        note.setAging(AllConst.timeout);
        note.setIsDel(1);
        note.setMessage(message);
        note.setMobile(mobile);

        note.setAppId(appId);
        note.setAppVer(appVer);
        note.setChannel(channel);

        note.setGmtCreated(new Date());
        note.setGmtUpdated(new Date());

        return note;
    }
}
