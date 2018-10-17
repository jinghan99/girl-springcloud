package com.yf.eurekaserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.yf.eurekaserver.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/17 22:40
 */
@RestController
@RequestMapping("eureka")
public class TestController {

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "this is eureka server !";
    }
}
