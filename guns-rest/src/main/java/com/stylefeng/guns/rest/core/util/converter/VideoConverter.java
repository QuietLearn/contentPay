package com.stylefeng.guns.rest.core.util.converter;

import com.stylefeng.guns.rest.core.common.constant.state.AllConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by hyj on 2018/11/27
 */


public class VideoConverter {

    public final static String FLV_PATH = FileOperationTool.DEFAULT_FLV_PATH;
    private static String PATH = "";
    private static String FILE_NAME = "";

    private static final Logger logger = LoggerFactory.getLogger(VideoConverter.class);

    public static boolean convert(File file) {

        PATH = file.getPath();
        FILE_NAME = file.getName();
        if (logger.isDebugEnabled()) {
            logger.debug("start to convert video to flv format...");
            logger.debug("the file name is : " + FILE_NAME);
            logger.debug("the file path is : " + PATH);
        }

        if (!checkfile(PATH)) {
            return false;
        } else {
            if (process()) {
                if (logger.isDebugEnabled()) {
                    logger.debug("process() ok");
                }
                return true;
            } else {
                return false;
            }
        }
    }

    private static boolean process() {
        int type = checkContentType();
        boolean status = false;
        if (type == 0) {
            if (logger.isDebugEnabled()) {
                logger.debug("Start to convert to flv file");
            }
            status = processMP4(PATH);// 直接将文件转为flv文件
        } else if (type == 1) {
            String avifilepath = processAVI(type);
            if (avifilepath == null) {
                return false;// avi文件没有得到
            }
            status = processMP4(avifilepath);// 将avi转为flv
        } else if (type == 9) {
            if (logger.isDebugEnabled()) {
                logger.debug("this file is no need to convert.");
            }
            return false;
        }
        return status;
    }

    private static int checkContentType() {
        String type = PATH.substring(PATH.lastIndexOf(".") + 1, PATH.length())
                .toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 0;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        }
        return 9;
    }

    // check file
    private static boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        }
        return true;
    }

    // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
    private static String processAVI(int type) {
        List<String> commend = new ArrayList<String>();
        commend.add(FLV_PATH + "mencoder");
        commend.add(PATH);
        commend.add("-oac");
        commend.add("lavc");
        commend.add("-lavcopts");
        commend.add("acodec=mp3:abitrate=64");
        commend.add("-ovc");
        commend.add("xvid");
        commend.add("-xvidencopts");
        commend.add("bitrate=600");
        commend.add("-of");
        commend.add("avi");
        commend.add("-o");
        commend.add(FLV_PATH + FILE_NAME.substring(0, FILE_NAME.lastIndexOf(".")) + ".avi");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process process = builder.start();
            doWaitFor(process);
            return FLV_PATH + FILE_NAME.substring(0, FILE_NAME.lastIndexOf(".")) + ".avi";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    private static boolean processMP4(String oldfilepath) {

        if (!checkfile(PATH)) {
            return false;
        }
        // 文件命名
        Calendar c = Calendar.getInstance();
        String savename = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);

        // 创建一个List集合来保存转换视频文件为flv格式的命令
        List<String> commend = new ArrayList<String>();
        commend.add(FLV_PATH + "ffmpeg");// 添加转换工具路径
        commend.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        commend.add(oldfilepath); // 添加要转换格式的视频文件的路径
        commend.add("-acodec");
        commend.add("copy");
        commend.add("-vcodec");
        commend.add("libx264");
        commend.add("-preset");
        commend.add("superfast");
        commend.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
        /*
        flv转码
        commend.add("-qscale");     //指定转换的质量
        commend.add("8");
        commend.add("-ab");        //设置音频码率
        commend.add("64");
        commend.add("-ac");        //设置声道数
        commend.add("2");
        commend.add("-ar");        //设置声音的采样频率
        commend.add("22050");
        commend.add("-r");        //设置帧频
        commend.add("24");*/

        /*commend.add("-s");
        commend.add("600x500");*/
        commend.add(FLV_PATH + FILE_NAME.substring(0, FILE_NAME.lastIndexOf(".")) + AllConst.TranscodeVideo.TRANSCODE_TYPE);

        try {
            Runtime runtime = Runtime.getRuntime();
            String cmd = "";
            String cut = FLV_PATH + "ffmpeg.exe -i "
                    + oldfilepath // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）-s 600x500
                    + " -y -f image2 -ss 8 -t 0.001 "
                    + FLV_PATH
                    + FILE_NAME.substring(0, FILE_NAME.lastIndexOf("."))
                    + AllConst.TranscodeVideo.COVER_TYPE;
            String cutCmd = cmd + cut;
            runtime.exec(cutCmd);
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process process = builder.start();
            int i = doWaitFor(process);
            if (i == 0) {
                if (logger.isDebugEnabled()) {
                    logger.debug("ffmpeg has finished.");
                }
            }
            process.destroy();
            deleteFile(oldfilepath);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteFile(String path){
        File file = new File(path);
        return file.delete();
    }

    public static int doWaitFor(Process p) {
        int exitValue = -1; // returned to caller when p is finished
        InputStream in = null;
        InputStream err = null;
        StringBuffer videoPrint = new StringBuffer();
        try {
            in = p.getInputStream();
            err = p.getErrorStream();
            boolean finished = false; // Set to true when p is finished

            while (!finished) {
                try {
                    while (in.available() > 0) {
                        // Print the output of our system call
                        Character c = new Character((char) in.read());
                        videoPrint.append(c);


                        /*if (logger.isDebugEnabled()) {
                            logger.debug("", c);
                        }*/
                    }
                    while (err.available() > 0) {
                        // Print the output of our system call
                        Character c = new Character((char) err.read());
                        videoPrint.append(c);
                        /*if (logger.isDebugEnabled()) {
                            logger.debug("", c);
                        }*/
                    }

                    if (logger.isDebugEnabled()) {
                        logger.debug( videoPrint.toString());
                    }
                    videoPrint.setLength(0);
                    // Ask the process for its exitValue. If the process
                    // is not finished, an IllegalThreadStateException
                    // is thrown. If it is finished, we fall through and
                    // the variable finished is set to true.
                    exitValue = p.exitValue();
                    finished = true;
                }catch (MalformedURLException e){
                    logger.error("MalformedURLException已捕获",e);
                } catch (IllegalThreadStateException e) {
                    Thread.currentThread();
                    // Process is not finished yet;
                    // Sleep a little to save on CPU cycles
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            // unexpected exception! print it out for debugging...
            if (logger.isErrorEnabled()) {
                logger.error("doWaitFor(): unexpected exception - " +
                        e.getMessage());
            }
            if (logger.isErrorEnabled()) {
                logger.error(e.getMessage());
            }
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            if (err != null) {
                try {
                    err.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());

                }
            }
        }

        // return completion status to caller
        return exitValue;
    }
}
