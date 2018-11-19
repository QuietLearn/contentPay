package com.stylefeng.guns.modular.repository.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.repository.service.IAppContentService;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IActiveService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyj on 2018/10/23
 */

@Api(tags = {"查找对应应用接口"})
@RestController
@RequestMapping("/front/appContent")
public class AppContentFrontController extends BaseController {

    @Autowired
    private IAppContentService appContentService;

    @ApiOperation(value = "查找",notes = "查找",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneType", value = "手机型号",  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phoneBrand", value = "手机品牌",  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "phoneSystem", value = "手机系统",  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "dpi", value = "分辨率",  dataType = "Integer", paramType = "query")
    })
    //response = Result.class
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result searchAppContent(String appId,String appVer,String channelId,String type){
       return appContentService.searchAppContent(appId,appVer,channelId,type);
    }


    /*@RequestMapping(value="/error")
    public Result Get(){
        throw new RuntimeException();
    }*/

}
