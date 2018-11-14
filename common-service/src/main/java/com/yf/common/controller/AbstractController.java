package com.yf.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller公共组件
 * @author zcl<yczclcn@163.com>
 */
public abstract class AbstractController {

	/**
	 * 日志
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());



	/**
	 * 重定向
	 * @param page
	 * @return 重定向全路径
	 */
	protected String redirect(String page) {
		return "redirect:".concat(page);
	}

	/**
	 * beetl视图
	 * @param page
	 * @return html全路径
	 */
	protected String html(String page) {
		return page.concat(".html");
	}
	
}
