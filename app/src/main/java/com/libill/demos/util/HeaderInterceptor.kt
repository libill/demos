package com.libill.demos.util

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // Test
        builder.addHeader("trace_id", "traceId")
        builder.removeHeader("trace_ids")
        return chain.proceed(builder.build())
    }
}