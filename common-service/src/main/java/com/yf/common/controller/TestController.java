package com.yf.common.controller;

import com.yf.common.service.ThreadPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.yf.common.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/22 17:46
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private ThreadPoolService threadPoolService;

    @RequestMapping("test")
    public Object testThreadPool(String name){
        threadPoolService.sayHello(name);
        return "this is test !";
    }
}
