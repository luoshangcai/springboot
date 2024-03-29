package com.study.cache.controller;

import com.alibaba.fastjson.JSON;
import com.study.cache.annotation.DistributedLock;
import com.study.cache.annotation.LockType;
import com.study.cache.annotation.RequestLimit;
import com.study.cache.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author luoshangcai
 * @Description //TODO 多级缓存查询数据
 * @Date 10:08 2020-06-24
 * @Param
 * @return
 **/
@RestController
@Slf4j
@RequestMapping("/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final TestService testService;

    public static List<String> list = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
    }

    /**
     * @return java.lang.String
     * @Description //TODO  分布式方法锁
     * 方法限流 限制  2秒只能调用2000次 设置的值应小于服务限流
     * @Param []
     **/
    @RequestMapping("/cache/check")
    @DistributedLock(lock = LockType.METHOD, timeOut = 15000)
    @RequestLimit(limit = 180, period = 1)
    public String cacheCheck(String id) {
        List<String> checkList = testService.cacheCheck(id);
        return JSON.toJSONString(checkList);
    }
}
