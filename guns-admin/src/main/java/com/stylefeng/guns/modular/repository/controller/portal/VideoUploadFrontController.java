package com.stylefeng.guns.modular.repository.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.util.converter.VideoConverter;
import com.stylefeng.guns.modular.repository.service.IAppContentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by hyj on 2018/10/23
 */

@Api(tags = {"资源库","查找对应应用内容接口"})
@RestController
@RequestMapping("/front/video")
public class VideoUploadFrontController extends BaseController {

    @Autowired
    private IAppContentService appContentService;


  /*  @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public void uploadVideo(MultipartFile file){

    }*/


    /*@RequestMapping(value = "/checkPermission",method = RequestMethod.POST)
    public Result checkPermission(String type,String uuidToken){
        return appContentService.checkPermission(type);
    }*/

    /*@RequestMapping(value="/error")
    public Result Get(){
        throw new RuntimeException();
    }*/

}
