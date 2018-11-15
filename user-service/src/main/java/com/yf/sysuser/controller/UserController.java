package com.yf.sysuser.controller;

import com.yf.sysuser.entity.UserEntiy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.yf.sysuser.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/11 15:02
 */
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    /**
     * 获取某个user
     * @return
     */
    @GetMapping("findById")
    public Object findById(String id){

        return new UserEntiy("我是"+id,"小"+id,18,"123456");
    }
}
