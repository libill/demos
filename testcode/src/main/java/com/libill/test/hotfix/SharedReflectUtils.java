package com.libill.test.hotfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SharedReflectUtils {

    public static Field findFiled(Object object, String name) throws NoSuchFieldException {
        Class<?> cls = object.getClass();
        while (cls != Object.class) {
            try {
                Field field = cls.getDeclaredField(name);
                if (field != null) {
                    return field;
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            cls = cls.getSuperclass();
        }
        throw new NoSuchFieldException(object.getClass().getSimpleName() + " not found " + name);
    }

    public static Method findMethod(Object object, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Class<?> cls = object.getClass();
        while (cls != Object.class) {
            try {
                Method method = cls.getDeclaredMethod(name, parameterTypes);
                if (method != null) {
                    return method;
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            cls = cls.getSuperclass();
        }
        throw new NoSuchMethodException(object.getClass().getSimpleName() + " not found " + name);
    }
}















