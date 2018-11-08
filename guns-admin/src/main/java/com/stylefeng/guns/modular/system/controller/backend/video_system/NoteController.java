package com.stylefeng.guns.modular.system.controller.backend.video_system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.page.PageInfoBT;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Member;
import com.stylefeng.guns.modular.system.warpper.AppInfoWarpper;
import com.stylefeng.guns.modular.system.warpper.AppWarpper;
import com.stylefeng.guns.modular.system.warpper.TypeWarpper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Note;
import com.stylefeng.guns.modular.system.service.INoteService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 短信管理控制器
 *
 * @author fengshuonan
 * @Date 2018-10-19 12:23:39
 */
@Controller
@RequestMapping("/note")
public class NoteController extends BaseController {

    private String PREFIX = "/system/note/";

    @Autowired
    private INoteService noteService;

    /**
     * 跳转到短信管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "note.html";
    }

    /**
     * 获取短信管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer appId,String mobile) {
        //注意改成server
        if (ToolUtil.isEmpty(mobile)&&appId==null){
            Page<Note> page =new PageFactory<Note>().defaultPage();

            page = noteService.selectPage(page);

            List<Note> NoteList = page.getRecords();
            page.setRecords((List<Note>)super.warpObject(new TypeWarpper(BeanKit.listToMapList(NoteList))));

            PageInfoBT<Note> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }else {
            Page<Note> page = new PageFactory<Note>().defaultPage();
            EntityWrapper<Note> entityWrapper = new EntityWrapper<>();
            if (appId!=null&&appId!=0){
                entityWrapper.eq("appId",appId);
            }
            if (ToolUtil.isNotEmpty(mobile)){
                entityWrapper.like("mobile","%"+mobile+"%");
            }

            page = noteService.selectPage(page,entityWrapper);
            List<Note> Notes = page.getRecords();
            page.setRecords((List<Note>)super.warpObject(new TypeWarpper(BeanKit.listToMapList(Notes))));
            PageInfoBT<Note> pageInfoBT =this.packForBT(page);

            return pageInfoBT;
        }
    }


    /**
     * 删除短信管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer noteId) {
        noteService.deleteById(noteId);
        return SUCCESS_TIP;
    }

    @ConditionalOnProperty(prefix = "batchDelete", name = "open", havingValue = "true")
    /**
     * 批量删除短信记录
     */
    @RequestMapping(value = "/delete_list")
    @ResponseBody
    public Object deleteNoteList(@RequestParam String ids) {
        String[] ss = ids.split(",");
        List<String>  noteIdList = Arrays.asList(ss);
        boolean deleteResult = noteService.deleteBatchIds(noteIdList);
        if (deleteResult){
            return SUCCESS_TIP;
        }
        return Result.createByErrorMessage("批量删除短信记录失败，请稍后再试");
    }


    /**
     * 短信管理详情
     */
    @RequestMapping(value = "/detail/{noteId}")
    @ResponseBody
    public Object detail(@PathVariable("noteId") Integer noteId) {
        return noteService.selectById(noteId);
    }
}
