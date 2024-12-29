package com.libill.demos.util

import android.app.Application
import com.didichuxing.doraemonkit.DoKit

object SdkInit {

    internal lateinit var appContext: Application

    fun init(context: Application) {
        appContext = context
        FontTask().run()
        initDoKit()
    }


    private fun initDoKit() {
        DoKit.Builder(appContext)
            //.productId("需要使用平台功能的话，需要到dokit.cn平台申请id")
            .build()
    }
}