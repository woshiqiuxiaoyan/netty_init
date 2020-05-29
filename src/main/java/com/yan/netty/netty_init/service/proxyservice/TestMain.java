package com.yan.netty.netty_init.service.proxyservice;

import com.yan.netty.netty_init.service.proxyservice.proxy.CglibProxy;
import com.yan.netty.netty_init.service.proxyservice.proxy.JdkProxy;

/**
 * <p>类名:TestMain </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2020/5/18
 * 创建时间: 下午5:38
 */
public class TestMain {


    public static void main(String[] args) {
        JdkProxy proxy= new JdkProxy();
        IHelloService helloService= (IHelloService)proxy.bind(new HelloServiceImpl());
        System.out.println(helloService.sayHello("大脸"));

        CglibProxy cglibProxy=new CglibProxy();
        IHelloService helloService1= (IHelloService)cglibProxy.bind(new HelloServiceImpl());
        System.out.println(helloService.sayHello("大脸儿"));
    }
}
