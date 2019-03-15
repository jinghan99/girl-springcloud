package com.yf.videoservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Package com.yf.videoservice.config
 * @Description: TODO
 * @author: jingh
 * @date 2019/3/15 22:55
 *
 * 自定义 yml 配置
 */
@Component
public class MyConst {

    @Value("${file.download_path}")
    private String downloadpath;

    public String getDownloadpath() {
        return downloadpath;
    }

    public void setDownloadpath(String downloadpath) {
        this.downloadpath = downloadpath;
    }
}
