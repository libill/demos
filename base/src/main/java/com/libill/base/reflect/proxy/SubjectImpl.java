package com.libill.base.reflect.proxy;

public class SubjectImpl implements ISubject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething");
    }
}
