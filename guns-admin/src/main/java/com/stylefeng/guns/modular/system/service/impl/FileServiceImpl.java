package com.stylefeng.guns.modular.system.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stylefeng.guns.core.util.file.FtpUtil;
import com.stylefeng.guns.modular.system.service.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Map;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

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

            bugLogString = getBugLogString(targetFile);
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




    private String getBugLogString(File file) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        BufferedReader br = null;
        StringBuilder clientBug = new StringBuilder();

        try {
            // 从文件系统中的某个文件中获取字节
            fis = new FileInputStream(file);
            // InputStreamReader 是字节流通向字符流的桥梁,
            isr = new InputStreamReader(fis);
            // 从字符输入流中读取文件中的内容,封装了一个new
            br = new BufferedReader(isr);
            // InputStreamReader的对象
            String str ="";
            while (( str= br.readLine()) != null) {
                //截取得到的一行数据
                clientBug.append(str);
                clientBug.append("\r\n");
                //跳过第一行
                //if(parms[0].equals("No")) continue;
                //把得到的数据放进list
                //调用添加方法，把list的第1,3,4,7条数据加入到mysql
                //write+=JDBCAdd.Insert(list.get(0),list.get(2),list.get(3),list.get(6));
            }
            //记录下读了/写了多少条数据
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return clientBug.toString();
    }

}
