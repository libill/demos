package com.libill.base;

import static org.junit.Assert.assertEquals;

import com.libill.base.reflect.ReflectTest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUnitTest {

    @Test
    public void testReflect()
            throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException,
            NoSuchFieldException {
        // 三种获取Class类对象
        Class clazz1 = ReflectTest.class;
        Class clazz2 = new ReflectTest().getClass();
        Class clazz = Class.forName("com.libill.base.reflect.ReflectTest");

        // 无参数构造方法
        Constructor csr = clazz.getConstructor();
        csr.setAccessible(true);
        Object o = csr.newInstance();

        // 有参数构造方法
        Constructor constructor = clazz.getConstructor(String.class);
        constructor.setAccessible(true);
        Object object = constructor.newInstance("666");

        // 反射类中的属性
        Field field = clazz.getDeclaredField("name");
        field.setAccessible(true);
        field.set(object, "777"); // object 是属性所在的类对象实例
        Object fieldValue = field.get(object);

        // 反射类中的方法
        Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
        setNameMethod.invoke(object, "999");

        assertEquals("999", ((ReflectTest) object).getName());
    }
}
