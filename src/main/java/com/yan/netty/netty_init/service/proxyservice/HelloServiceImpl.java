package com.yan.netty.netty_init.service.proxyservice;

/**
 * <p>类名:HelloServiceImpl </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2020/5/18
 * 创建时间: 下午5:30
 */
public class HelloServiceImpl implements IHelloService {

    @Override
    public String sayHello(String name){
        System.out.println("hello"+name);
        return "hello";
    }
}
