package com.stylefeng.guns.modular.order.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.Order;
import com.stylefeng.guns.modular.order.service.IOrderService;

import java.util.List;

/**
 * order控制器
 *
 * @author hyj
 * @Date 2018-10-12 17:13:24
 */
@Controller
@RequestMapping("/order")
public class OrderController<T> extends BaseController {

    private String PREFIX = "/order/order/";

    @Autowired
    private IOrderService orderService;

    /**
     * 跳转到order首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到添加order
     */
    @RequestMapping("/order_add")
    public String orderAdd() {
        return PREFIX + "order_add.html";
    }

    /**
     * 跳转到修改order
     */
    @RequestMapping("/order_update/{orderId}")
    public String orderUpdate(@PathVariable Integer orderId, Model model) {
        Order order = orderService.selectById(orderId);
        model.addAttribute("item",order);
        LogObjectHolder.me().set(order);
        return PREFIX + "order_edit.html";
    }

    /**
     * 获取order列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Order> list(String condition) {
        return orderService.selectList(null);
    }

    /**
     * 新增order
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Order order) {
        orderService.insert(order);
        return SUCCESS_TIP;
    }

    /**
     * 删除order
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer orderId) {
        orderService.deleteById(orderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改order
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Order order) {
        orderService.updateById(order);
        return SUCCESS_TIP;
    }

    /**
     * order详情
     */
    @RequestMapping(value = "/detail/{orderId}")
    @ResponseBody
    public Object detail(@PathVariable("orderId") Integer orderId) {
        return orderService.selectById(orderId);
    }
}
