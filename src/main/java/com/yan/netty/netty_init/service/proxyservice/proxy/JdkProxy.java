package com.yan.netty.netty_init.service.proxyservice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>类名:JdkProxy </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2020/5/18
 * 创建时间: 下午4:33
 */
public class JdkProxy implements InvocationHandler {


    private Object targetObj;

    public Object bind(Object targetObj){
        this.targetObj=targetObj;
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader()
                ,targetObj.getClass().getInterfaces(),this);
    }


    @Override
    public Object invoke(Object proxy,Method method,Object[] args) throws Throwable {

        Object result=null;
        System.out.println("事务开始："+args);

        if(args[0] instanceof String){
            args[0]+="~(jdkproxy)";
        }
        result= method.invoke(targetObj,args);

        System.out.println("事务end："+args);

        return result;
    }
}
