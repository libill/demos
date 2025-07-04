package com.libill.test.hotfix;

import android.app.Application;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HotFix {

    /**
     * 1、获取到当前应用的PathClassloader
     * <p>
     * 2、反射获取到DexPathList属性对象的pathList;
     * <p>
     * 3、反射修改pathList的dexElements
     * 3.1、把补丁包patch.dex转化为Element[] (path)
     * 3.2、获得pathList的dexElements属性 (old)
     * 3.3、patch+old合并，并反射赋值给pathList的dexElements
     */
    public static void installPath(Application application, File patch) {
        if (!patch.exists()) {
            return;
        }
        /**
         * 1、获取到当前应用的PathClassloader
         */
        ClassLoader classLoader = application.getClassLoader();

        /**
         * 2、反射获取到DexPathList属性对象的pathList;
         */
        try {
            Field pathListField = SharedReflectUtils.findFiled(classLoader, "pathList");
            Object pathList = pathListField.get(classLoader);

            /**
             * 3、反射修改pathList的dexElements
             */
            // 3.1、把补丁包patch.dex转化为Element[] (path)
            Method makePathElements = SharedReflectUtils.findMethod(pathList, "makePathElements",
                    List.class, File.class, List.class);
            ArrayList<Object> patchList = new ArrayList<>();
            patchList.add(patch);
            ArrayList<IOException> suppressedExceptions = new ArrayList<IOException>();
            // 补丁包转化为Elements数组
            Object[] patchElements = (Object[]) makePathElements.invoke(null, patchList, application.getCacheDir(), suppressedExceptions);

            // 3.2、获得pathList的dexElements属性 (old)
            Field dexElementsFiled = SharedReflectUtils.findFiled(pathList, "dexElements");
            Object[] dexElements = (Object[]) dexElementsFiled.get(patchList);

            // 3.3、patch+old合并，并发射赋值给pathList的dexElements
            //创建新的ELement数组
            Object[] newElements = (Object[]) Array.newInstance(patchElements.getClass().getComponentType(),
                    patchElements.length + dexElements.length);
            System.arraycopy(patchElements, 0, newElements, 0, patchElements.length);
            System.arraycopy(patchElements, 0, newElements, patchElements.length, dexElements.length);
            dexElementsFiled.set(pathList, newElements);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}






















