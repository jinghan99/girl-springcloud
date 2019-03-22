package com.yf.videoservice.utils;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Package com.girl.video.utils
 * @Description: 文件操作类
 * @author: jingh
 * @date 2019/2/11 15:33
 */
public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 获取文件夹下所有 视频文件 （递归）
     *
     * @param
     * @return
     */
    public static void getVideoList(List<File> fileList, String path) {
        File dir = new File(path);
        logger.info("path:" + path);
        if (dir.exists()) {
            File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
            logger.info("dir.listFiles() length:" + dir.listFiles().length);
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) { // 判断是文件还是文件夹
                        getVideoList(fileList, files[i].getAbsolutePath()); // 获取文件绝对路径
                    } else if (isVideo(files[i])) { // 是否视频文件
                        fileList.add(files[i]);
                    } else {
                        continue;
                    }
                }
            }
        }

    }

    /**
     * 判断文件是否视频文件
     *
     * @param file
     * @return
     */
    public static boolean isVideo(File file) {
//        .mp4 结尾 或 .flv 结尾 等等
        String reg = "(.mp4$|.flv$|.f4v$|.avi$|.rm$|.mkv$|.rmvb$|.wmv$|.avi$)";
        Pattern p = Pattern.compile(reg);
        if (p.matcher(file.getName()).find()) {
            logger.info("判断文件是否视频文件 true--- 文件名：《{}》  ", file.getName());
            return true;
        }
        logger.info("判断文件是否视频文件 false--- 文件名：《{}》  ", file.getName());
        return false;
    }


    /**
     * 删除文件
     *
     * @param path
     */
    public static Boolean delFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    FileUtils.deleteDirectory(file);
                }
                if (file.delete()) {
                    logger.info("文件删除成功！目标文件：{}", path);
                    return true;
                } else {
                    logger.error("文件删除失败! 目标文件：{}", path);
                    return false;
                }
            }
            return false;
        } catch (IOException e) {
            logger.error("文件删除失败! 目标文件：{}", path);
            return false;
        }
    }


    public static void main(String[] args) {

    }


}
