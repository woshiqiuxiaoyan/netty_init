package com.yan.netty.netty_init.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>类名:TestController </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2020/5/29
 * 创建时间: 下午5:05
 */
@RestController
public class TestController {


    @GetMapping ("/test001")
    public String test001(){

        return "success";

    }

}
