package com.libill.test.router;

import android.app.Activity;

import java.util.Map;

public class FoodRouter implements IRouteLoad {

    @Override
    public void loadInto(Map<String, Class<? extends Activity>> routers) {
        routers.put("/food/test", TestActivity.class);
        routers.put("/food/test", TestActivity.class);
        routers.put("/food/test", TestActivity.class);
        routers.put("/food/test", TestActivity.class);
        routers.put("/food/test", TestActivity.class);
    }
}
