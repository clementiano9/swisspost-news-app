package com.clement.data.network

import com.clement.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .client(OkHttpClientProvider.getOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    fun getApiService(): ApiInterface {
        return getRetrofitInstance().create(ApiInterface::class.java)
    }
}