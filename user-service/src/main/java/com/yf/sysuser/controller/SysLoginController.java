package com.yf.sysuser.controller;

import com.yf.common.annotation.SysLog;
import com.yf.common.utils.MD5Utils;
import com.yf.sysuser.properties.GlobalProperties;
import com.yf.sysuser.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 用户controller
 * @author zcl<yczclcn@163.com>
 */
@Controller
public class SysLoginController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private GlobalProperties globalProperties;

	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {

		return html("/login");
	}
	
	/**
	 * 登录
	 */
	@SysLog("登录")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model) {
		String username = getParam("username").trim();
		String password = getParam("password").trim();

		return html("/login");
	}

	/**
	 * 跳转后台控制台
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return html("/index");
	}
	
	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return html("/login");
	}
	
}
