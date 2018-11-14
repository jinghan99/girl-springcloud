package com.yf.common.controller;

import com.yf.common.service.ThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TestController extends AbstractController{


    @Autowired
    private ThreadPoolService threadPoolService;

    @RequestMapping("test")
    public Object testThreadPool(String name){
        threadPoolService.sayHello(name);
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");
        return "this is test !";
    }
}
