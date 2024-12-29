package com.libill.demos.test;

/**
 * 类加载测试类
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.libill.demos.activity.Test3.java
 * @author: liqiongwei
 * @date: 2016-03-02 15:26
 * see http://www.cnblogs.com/lijunamneg/archive/2013/03/25/2980991.html
 */
public class ClassLoadingTest {
    static {
        System.out.println("static here in ClassLoadingTest");
    }

    public static void main(String[] arg) {
        Circle circle = new Circle();
        circle.setWith();
        System.out.println("" + AA.a);
    }
}

class Shape {
    static {
        System.out.println("static here in Shape");
    }

    public Shape() {
        System.out.println("I am Shape ");
    }

    public void setWith() {
        System.out.println("setWith here in Shape");
    }
}

class Circle extends Shape {
    static {
        System.out.println("static here in Cicle");
    }


    public Circle() {
        System.out.println("I am Circle ");
    }

    @Override
    public void setWith() {
        super.setWith();
        System.out.println("setWith here in Circle");
    }
}

class AA {

    static {
        System.out.println("static here in AA ");
    }

    public static final double a = 3 + 1; //不加载类
    public static double b = 3 + 1; // 加载类
}


