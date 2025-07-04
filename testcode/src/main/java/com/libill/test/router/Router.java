package com.libill.test.router;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.alibaba.android.arouter.utils.ClassUtils;

import java.util.HashMap;
import java.util.Set;

public class Router {
    private static volatile Router instance = null;

    private Router() {
    }

    public static Router getInstance() {
        if (instance == null) {
            synchronized (Router.class) {
                if (instance == null) {
                    instance = new Router();
                }
            }
        }
        return instance;
    }

    private HashMap<String, Class<? extends Activity>> routers = new HashMap<>();

    public void register(String path, Class<? extends Activity> cls) {
        routers.put(path, cls);
    }

    public void startActivity(Activity activity, String path) {
        Class<? extends Activity> cls = routers.get(path);
        if (cls != null) {
            Intent intent = new Intent(activity, cls);
            activity.startActivity(intent);
        }
    }

    public void init(Application application) {
        try {
            Set<String> classNames = ClassUtils.getFileNameByPackageName(application, "com.libill.test.router");
            for (String className : classNames) {
                Class<?> cls = Class.forName(className);
                if (IRouteLoad.class.isAssignableFrom(cls)) {
                    IRouteLoad iRouteLoad = (IRouteLoad) cls.newInstance();
                    iRouteLoad.loadInto(routers);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
















