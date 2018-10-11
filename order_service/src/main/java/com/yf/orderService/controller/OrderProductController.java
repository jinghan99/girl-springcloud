package com.yf.orderService.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yf.orderService.service.OrderProductService;
import com.yf.orderService.utils.RedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Package com.yf.orderService.controller
 * @Description: TODO
 * @author: jingh
 * @date 2018/10/9 23:14
 */
@RestController
@RequestMapping("api/v1/order")
public class OrderProductController {


    @Autowired
    private OrderProductService orderProductService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "saveFallback")
    public Object save(String orderId, String productId, HttpServletRequest request){

        String token = request.getHeader("token");

        String cookie = request.getHeader("cookie");

        System.out.println("token ="+token);

        System.out.println("cookie ="+cookie);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "抢购成功");
        map.put("orderProduct", orderProductService.save(orderId, productId));
        return map;
    }

    /**
     * 熔断机制 开启
     * 方法名 一定与 api 一致
     * @param OrderId
     * @param ProductId
     * @return
     */
    private Object saveFallback(String OrderId, String ProductId,HttpServletRequest request) {
        String saveOrderKey = "save-Order";

        String value = redisUtils.get(saveOrderKey);

        /**
         * 用线程  防止堵塞
         */
        String ip = request.getRemoteHost();

//        扩展 线程池 用于调用短信
        new Thread(()->{
            if(StringUtils.isBlank(value)){
                System.out.println("紧急通知，用户下单失败，请查找原因 ip："+ip);
                redisUtils.set(saveOrderKey,"saveFallback",20, TimeUnit.SECONDS);
                //调用 短信服务
            }else{
                System.out.println("已发送过短信,20s内不发送短信");
            }
        }).start();

        Map<String, Object> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "当前抢购人数过多 稍后重试");
        map.put("value", value);
        return map;
    }
}
