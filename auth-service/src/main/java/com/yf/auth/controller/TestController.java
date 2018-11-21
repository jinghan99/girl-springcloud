package com.yf.auth.controller;

import com.yf.auth.entiy.SysUserEntity;
import com.yf.auth.service.SysUserService;
import com.yf.utils.entiy.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package com.yf.auth.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/11/21 16:25
 */
@RestController
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/{name}")
    public SysUserEntity test(@PathVariable  String name){
        return sysUserService.getByUserName(name) ;
    }
}
