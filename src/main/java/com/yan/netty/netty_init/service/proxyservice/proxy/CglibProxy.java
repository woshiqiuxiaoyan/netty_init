package com.yan.netty.netty_init.service.proxyservice.proxy;

import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * <p>类名:CglibProxy </p>
 * <p>描述:JDK的动态代理机制只能代理实现了接口的类
 * ，而不能实现接口的类就不能实现JDK的动态代理
 * ，cglib是针对类来实现代理的
 * ，他的原理是对指定的目标类生成一个子类
 * ，并覆盖其中方法实现增强
 * ，但因为采用的是继承
 * ，所以不能对final修饰的类进行代理。 </p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2020/5/18
 * 创建时间: 下午5:51
 */
public class CglibProxy implements MethodInterceptor {

    private Object targetObj;

    public Object bind(Object targetObj) {
        this.targetObj = targetObj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();

    }

    @Override
    public Object intercept(Object o,Method method,Object[] objects,MethodProxy methodProxy) throws Throwable {

        Object result = null;
        System.out.println("事务开始：" + objects);

        if (null != objects && objects.length > 0 && objects[0] instanceof String) {
            objects[0] += "~(cglibproxy)";
        }
        result = methodProxy.invoke(o,objects);

        System.out.println("事务end：" + objects);

        return result;
    }
}
