package com.yf.common.service.impl;

import com.yf.common.service.ThreadPoolService;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 线程池 实现
 * @author: jingh
 * @date 2018/10/22 17:39
 */
@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {

    /**
     *  @Async 异步 执行 调用默认线程池
     * @param name
     */
    @Async
    @Override
    public void sayHello(String name) {
        LoggerFactory.getLogger(ThreadPoolServiceImpl.class).info(name + ":Hello World!");
    }
}
