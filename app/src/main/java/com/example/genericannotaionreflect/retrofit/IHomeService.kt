package com.example.genericannotaionreflect.retrofit

import okhttp3.Call
import okhttp3.OkHttpClient
interface IHomeService {

    companion object {
        val instance =  Retrofit(OkHttpClient.Builder().build()).create(IHomeService::class.java)

        fun invoke(): IHomeService {
            return instance
        }
    }

    @GET("banner/json")
    suspend fun getBanner(): Call
}