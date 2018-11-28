package com.walker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot helloworld controller
 *
 * @author Walker
 * @date 2018/10/18 下午5:11
 */
@RestController
public class HelloWorldController {
    //RestController的意思是Controller里面的方法返回的数据都以json格式输出

    @RequestMapping("/helloWorld")
    public String helloWorld() {
        return "hello world!";
    }
}
