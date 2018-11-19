package com.yf.common.utils;
/**
 * 字符串相关方法
 *
 */
public class StringUtil {


	
	/**
	 * 对象非空处理 	
	 * @param obj
	 * @return
	 */
	public static String nullToString(Object obj){
    	if(null == obj){
    		return "";
    	}else{
    		return obj.toString();
    	}
    }

}
