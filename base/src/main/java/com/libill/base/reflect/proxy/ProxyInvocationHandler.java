package com.libill.base.reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    private Object realSubject;

    public ProxyInvocationHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1、在转调具体目标对象之前，可以执行一些功能处理
        // 2、调用具体目标对象的方法
        Object result = method.invoke(realSubject, args);
        // 3、在转调具体目标对象之后，可以执行一些功能处理
        return result;
    }
}
