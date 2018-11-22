package com.stylefeng.guns.modular.repository.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.modular.repository.service.IAppContentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hyj on 2018/10/23
 */

@Api(tags = {"资源库","查找对应应用内容接口"})
@RestController
@RequestMapping("/front/videoAppSource")
public class VideoAppSourceFrontController extends BaseController {

    @Autowired
    private IAppContentService appContentService;

    @ApiOperation(value = "查找",notes = "查找",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "应用id",  required =true,dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appVer", value = "应用渠道",  required =true,dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "channelId", value = "分发渠道",required =true,  dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "需要数据类型", required =true, dataType = "string", paramType = "query")
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
