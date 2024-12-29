package com.libill.demos

import android.app.Application
import com.libill.demos.util.SdkInit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SdkInit.init(this)
    }
}