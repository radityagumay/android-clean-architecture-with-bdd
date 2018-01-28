package com.radityalabs.moviefinder.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
                .addHeader("Content-Type", "application/json;charset=utf-8")
                .build()
        return chain.proceed(request)
    }
}
