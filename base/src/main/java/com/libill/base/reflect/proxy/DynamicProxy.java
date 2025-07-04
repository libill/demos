package com.libill.base.reflect.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxy {

    public static void main(String[] args) {
        SubjectImpl subject = new SubjectImpl();
        ISubject proxySubject = (ISubject) Proxy.newProxyInstance(
                ISubject.class.getClassLoader(),
                new Class[]{ ISubject.class},
                new ProxyInvocationHandler(subject)
        );

        // 使用代理对象调用方法
        proxySubject.doSomething();
    }
}
