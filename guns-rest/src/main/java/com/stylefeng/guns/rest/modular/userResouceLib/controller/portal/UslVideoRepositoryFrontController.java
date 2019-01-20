package com.stylefeng.guns.rest.modular.userResouceLib.controller.portal;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.rest.core.common.result.Result;
import com.stylefeng.guns.rest.modular.userResouceLib.service.IFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 发布视频控制器
 *
 * @author fengshuonan
 * @Date 2018-11-27 15:20:46
 */
@RestController
@RequestMapping("/front/uslVideoRepository")
public class UslVideoRepositoryFrontController extends BaseController {


    @Autowired
    private IFileService fileService;

    /**
     * 跳转到添加发布视频
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public Result uslVideoRepositoryAdd(HttpServletRequest request, MultipartFile file) {
        // 获取配置的转换工具（ffmpeg.exe）的存放路径
        String ffmpegPath = request.getSession().getServletContext().getRealPath("/tools/ffmpeg.exe");
        String path = request.getSession().getServletContext().getRealPath("upload");

        String uploadVideo = fileService.uploadVideo(file, path);

        if(StringUtils.isBlank(uploadVideo)){
            return Result.createBySuccessMessage("上传视频失败");
        }
        return Result.createBySuccessMessage("上传视频成功，等待审核");
    }

}