package com.stylefeng.guns.rest.modular.userResouceLib.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stylefeng.guns.rest.core.common.constant.state.AllConst;
import com.stylefeng.guns.rest.core.util.converter.VideoConverter;
import com.stylefeng.guns.rest.core.util.file.FtpUtil;
import com.stylefeng.guns.rest.modular.userResouceLib.model.UslVideoRepository;
import com.stylefeng.guns.rest.modular.userResouceLib.service.IFileService;
import com.stylefeng.guns.rest.modular.userResouceLib.service.IUslVideoRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private IUslVideoRepositoryService iUslVideoRepositoryService;

    public Map uploadPhoto(MultipartFile file, String path){
        String originalFilename = file.getOriginalFilename();
        //jpg  jpeg png 文件结尾
        String suffix  = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        String uploadFilename = UUID.randomUUID().toString()+"."+suffix;

        Map map = Maps.newHashMap();
        String bugLogString = "";
//        String path = request.getContextPath()+"/upload/"+((User)(request.getSession().getAttribute(Const.CURRENT_USER))).getId()+"/";

        logger.info("（开始）上传文件，文件放置路径{}，旧文件名{}，新文件名{}",path,originalFilename,uploadFilename);

        File fileDir = new File(path);
        if (!fileDir.exists()){
            //毕竟tomcat的用户对里面工程可能没有创建文件夹的权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path , uploadFilename);

        try {
            file.transferTo(targetFile);
            /*List<File> fileList =  Lists.newArrayList();
            fileList.add(targetFile);*/
            //文件已经上传成功了
            map.put("targetFile",targetFile);

            map.put("bugLogString",bugLogString);
            // list为以后多文件上传扩展使用
            // 当时是因为没有在linux的 /ftpfile文件创建img并赋予ftpuser权限导致不能写入的原因
            if (!FtpUtil.uploadFile(Lists.newArrayList(targetFile),"log/")){
                return null; //如果没有将文件写入ftp服务器，返回的文件名为""代表失败，因为返回string，不知道如何表示错误
            }

            //已经上传到ftp服务器上

        } catch (IOException e) {
            logger.error("文件上传到目标目录异常",e);
            return null;
        } finally {
            targetFile.delete();
        }
        return map;
    }


    public String uploadVideo(MultipartFile file, String path){
        String originalFilename = file.getOriginalFilename();


        String prefix = originalFilename.substring(0,originalFilename.lastIndexOf("."));
        //avi mp4 flv 文件结尾
        String suffix  = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

        String uploadFilename = UUID.randomUUID().toString()+"."+suffix;

        logger.info("（开始）上传文件，文件放置路径{}，旧文件名{}，新文件名{}",path,originalFilename,uploadFilename);

        File fileDir = new File(path);
        if (!fileDir.exists()){
            //毕竟tomcat的用户对里面工程可能没有创建文件夹的权限
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path , uploadFilename);
        File transcodingVideo = null;
        File video_cover = null;
        try {
            //文件已经上传成功了
            file.transferTo(targetFile);
            if (!VideoConverter.convert(targetFile)){
                logger.error("转码失败");
            }
            String targetFileName = targetFile.getName().substring(0, targetFile.getName().lastIndexOf("."));
            transcodingVideo = new File(VideoConverter.FLV_PATH + targetFileName + AllConst.TranscodeVideo.TRANSCODE_TYPE);


            video_cover = new File(VideoConverter.FLV_PATH + targetFileName + AllConst.TranscodeVideo.COVER_TYPE);

            // list为以后多文件上传扩展使用
            // 当时是因为没有在linux的 /ftpfile文件创建img并赋予ftpuser权限导致不能写入的原因
            if (!FtpUtil.uploadFile(Lists.newArrayList(transcodingVideo),"video/")){
                return null; //如果没有将文件写入ftp服务器，返回的文件名为""代表失败，因为返回string，不知道如何表示错误
            }

            if (!FtpUtil.uploadFile(Lists.newArrayList(video_cover),"img/")){
                return null; //如果没有将文件写入ftp服务器，返回的文件名为""代表失败，因为返回string，不知道如何表示错误
            }

            /**
             * 插入上传视频信息到数据库
             */
            UslVideoRepository uslVideoRepository = assemUslVideo(targetFileName,prefix);
            boolean insert = iUslVideoRepositoryService.insert(uslVideoRepository);
            if (!insert){
                logger.error("插入失败");
            }

        } catch (IOException e) {
            logger.error("文件上传到目标目录异常",e);
            return null;
        } finally {
            targetFile.delete();
            //transcodingVideo.delete();
            video_cover.delete();
        }
        return targetFile.getName();
    }

    public UslVideoRepository assemUslVideo(String urlFile, String title){
        UslVideoRepository uslVideoRepository = new UslVideoRepository();
        uslVideoRepository.setGmtCreated(new Date());
        uslVideoRepository.setGmtModified(new Date());
        uslVideoRepository.setVideoAddress(urlFile + AllConst.TranscodeVideo.TRANSCODE_TYPE);
        uslVideoRepository.setTitle(title);

        uslVideoRepository.setCoverImage(urlFile + AllConst.TranscodeVideo.COVER_TYPE);
        return uslVideoRepository;
    }


}
