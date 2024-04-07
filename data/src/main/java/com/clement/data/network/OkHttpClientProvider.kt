package com.clement.data.network

import okhttp3.OkHttpClient

object OkHttpClientProvider {
    fun getOkHttpClient() = OkHttpClient.Builder()
        .build()
}