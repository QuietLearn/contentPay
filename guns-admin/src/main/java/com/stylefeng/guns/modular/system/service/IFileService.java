package com.stylefeng.guns.modular.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IFileService {
    File uploadPhoto(MultipartFile file, String path);

    String getBugLogString(File file);
}
