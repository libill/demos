package com.libill.base.reflect;

public class ReflectTest {

    public ReflectTest() {
        this.name = "";
    }

    public ReflectTest(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
