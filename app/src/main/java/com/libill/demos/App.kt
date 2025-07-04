package com.libill.demos

import android.app.Application
import android.content.Context
import com.libill.demos.util.SdkInit

class App : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        SdkInit.init(this)
    }
}