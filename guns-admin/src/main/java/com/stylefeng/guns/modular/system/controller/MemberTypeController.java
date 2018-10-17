package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.MemberType;
import com.stylefeng.guns.modular.system.service.IMemberTypeService;

import java.util.List;

/**
 * 会员购买类型控制器
 *
 * @author fengshuonan
 * @Date 2018-10-15 12:36:04
 */
@Controller
@RequestMapping("/memberType")
public class MemberTypeController extends BaseController {

    private String PREFIX = "/system/memberType/";

    @Autowired
    private IMemberTypeService memberTypeService;

    /**
     * 跳转到会员购买类型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "memberType.html";
    }

    /**
     * 跳转到添加会员购买类型
     */
    @RequestMapping("/memberType_add")
    public String memberTypeAdd() {
        return PREFIX + "memberType_add.html";
    }

    /**
     * 跳转到修改会员购买类型
     */
    @RequestMapping("/memberType_update/{memberTypeId}")
    public String memberTypeUpdate(@PathVariable Integer memberTypeId, Model model) {
        MemberType memberType = memberTypeService.selectById(memberTypeId);
        model.addAttribute("item",memberType);
        LogObjectHolder.me().set(memberType);
        return PREFIX + "memberType_edit.html";
    }

    /**
     * 获取会员购买类型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<MemberType> list(String condition) {
        return memberTypeService.selectList(null);
    }

    /**
     * 新增会员购买类型
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MemberType memberType) {
        memberTypeService.insert(memberType);
        return SUCCESS_TIP;
    }

    /**
     * 删除会员购买类型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer memberTypeId) {
        memberTypeService.deleteById(memberTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改会员购买类型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MemberType memberType) {
        memberTypeService.updateById(memberType);
        return SUCCESS_TIP;
    }

    /**
     * 会员购买类型详情
     */
    @RequestMapping(value = "/detail/{memberTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("memberTypeId") Integer memberTypeId) {
        return memberTypeService.selectById(memberTypeId);
    }
}
