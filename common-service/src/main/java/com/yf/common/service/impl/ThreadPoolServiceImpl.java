package com.yf.common.service.impl;

import com.yf.common.service.ThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * 线程池 实现
 * @author: jingh
 * @date 2018/10/22 17:39
 */
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {
    private Logger log=LoggerFactory.getLogger(ThreadPoolServiceImpl.class);

    public static Random random = new Random();
    /**
     *  @Async 异步 执行 调用默认线程池
     * @param name
     */
    @Async
    @Override
    public void sayHello(String name) {
        log.info(name + ":Hello World!");
        log.info("开始做任务 ："+name);
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            log.error("完成任务， 失败"+name);
        }
        long end = System.currentTimeMillis();
        log.info("完成任务，耗时："+name + (end - start) + "毫秒");
    }


}
