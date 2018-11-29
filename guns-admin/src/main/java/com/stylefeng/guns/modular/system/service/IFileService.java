package com.stylefeng.guns.modular.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface IFileService {
    Map uploadPhoto(MultipartFile file, String path);

}
