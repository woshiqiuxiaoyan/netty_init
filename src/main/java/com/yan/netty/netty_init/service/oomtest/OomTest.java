package com.yan.netty.netty_init.service.oomtest;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>类名:OomTest </p>
 * <p>描述:</p>
 * 创建方式 IntelliJ IDEA.
 * 创建人: qxy
 * 创建日期: 2020/5/20
 * 创建时间: 下午5:27
 */
public class OomTest {

    public static void main(String[] args) {

        List<E> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(1000);
                list.add(new E());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}

class E {

    private String name;
}
