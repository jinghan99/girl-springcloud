package com.yf.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 * @author zcl<yczclcn@163.com>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
