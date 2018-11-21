package com.yf.auth.controller;

import com.yf.auth.service.SysUserService;
import com.yf.utils.annotation.SysLog;
import com.yf.utils.common.CommonUtils;
import com.yf.utils.entiy.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用户controller
 * @author zcl<yczclcn@163.com>
 */
@RestController
@RequestMapping("/auth")
public class SysLoginController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;



	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value = "/toLogin", method = RequestMethod.GET)
	public R toLogin() {

		return R.error("跳转到登录页面");
	}
	
	/**
	 * 登录
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public R login(String username, String password) {
		String token = sysUserService.login(username,password);
		return CommonUtils.msg(token);
	}

	/**
	 * 跳转后台控制台
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public R index() {
		return R.error("跳转后台控制台");
	}
	
	/**
	 * 退出
	 */
	@SysLog("退出系统")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public R logout() {
		return R.error("退出系统");
	}
	
}
