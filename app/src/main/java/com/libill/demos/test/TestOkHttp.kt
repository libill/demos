package com.libill.demos.test

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import okhttp3.OkHttpClient
import okhttp3.Request

class TestOkHttp {
    fun requestHttpGet(url: String) {
        val client = OkHttpClient.Builder().build()

        val request = Request.Builder()
            .url(url)
            .build()
        kotlin.runCatching {
            val response = client.newCall(request).execute()
            //val response = client.newCall(request).enqueue()
            response.body.toString()
        }
    }

    fun testGlide(context: Context, imageView: ImageView) {
        Glide.with(context).load("").into(imageView)
    }
}