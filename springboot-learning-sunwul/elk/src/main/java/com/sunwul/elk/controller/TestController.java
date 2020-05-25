package com.sunwul.elk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/*****
 * @author: Sunwul
 * @date: 2020/2/16 00:06
 * PS: 日志等级
 * logger.info("info=====这是详情");
 * logger.warn("warn=====这是警告信息");
 * logger.error("error=====这是错误信息");
 */
@RestController
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/id/{id}")
    public String getId(@PathVariable String id){
        logger.info("Controller输出的ID信息: "+id);
        return "Hello"+id;
    }

    @GetMapping("/name/{name}")
    public String getName(@PathVariable String name){
        logger.info("Controller输出的name信息: "+name);
        return "Hello"+name;
    }
}
