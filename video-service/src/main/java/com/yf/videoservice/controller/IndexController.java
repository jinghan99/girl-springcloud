package com.yf.videoservice.controller;

import com.yf.videoservice.config.MyConst;
import com.yf.videoservice.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.yf.videoservice.controller
 * @Description: TODO
 * @author: jingh
 * @date 2019/3/15 22:50
 */
@RestController
public class IndexController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MyConst myConst;

    @GetMapping("/")
    public String index() {
        return redisUtils.get("12");
    }
    @GetMapping("/value")
    public String value(){
        return myConst.getDownloadpath();
    }

}
