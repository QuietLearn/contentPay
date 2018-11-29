package com.stylefeng.guns.rest.modular.userResouceLib.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IFileService {

    String uploadVideo(MultipartFile file, String path);
}
