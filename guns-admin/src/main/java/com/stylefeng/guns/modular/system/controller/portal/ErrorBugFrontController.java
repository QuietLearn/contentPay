package com.stylefeng.guns.modular.system.controller.portal;

import com.google.common.collect.Maps;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.result.Result;
import com.stylefeng.guns.core.log.LogManager;
import com.stylefeng.guns.core.log.factory.MemberLogTaskFactory;
import com.stylefeng.guns.core.util.PropertiesUtil;
import com.stylefeng.guns.modular.system.model.BuriedPoint;
import com.stylefeng.guns.modular.system.service.IActiveService;
import com.stylefeng.guns.modular.system.service.IFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hyj on 2018/10/23
 */

@RestController
@RequestMapping("/front/error_bug")
public class ErrorBugFrontController  extends BaseController{
    @Autowired
    private IFileService iFileService;

    @RequestMapping("/upload")
    @ResponseBody
    public Result richtextUpload(HttpSession session, @RequestParam(value = "uploadFile",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map = Maps.newHashMap();

        //返回上传图片的结果
        request.getServletContext().getRealPath("upload");

        String path = request.getSession().getServletContext().getRealPath("upload");

        File targetFile = iFileService.uploadPhoto(file, path);
        if (targetFile==null){
            return Result.createByErrorMessage("文件上传到服務器异常");
        }
        String bugLogString = iFileService.getBugLogString(targetFile);
        LogManager.me().executeLog(MemberLogTaskFactory.androidBugLog(2,bugLogString));

        String targetFileName = targetFile.getName();
        if(StringUtils.isBlank(targetFileName)){
            map.put("msg","上传失败");
            return Result.createByErrorMessage(map.get("msg"));
        }
        map.put("msg","上传成功");
        //map.put("file_path", PropertiesUtil.getProperty("ftp.server.http.prefix")+"/"+targetFileName);
        //在服务器响应客户端的时候，带上Access-Control-Allow-Origin头信息，是解决跨域的一种方法
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");
//            return iFileService.uploadPhoto(file,request);
        return Result.createBySuccessMessage(map.get("msg"));
        }



}
