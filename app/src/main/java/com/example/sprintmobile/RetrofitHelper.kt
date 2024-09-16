package com.example.sprintmobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val BASE_URL = "https://api.openai.com/" // Verifique se está correto

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getChatGPTApiService(): ChatGPTApiService {
        return retrofit.create(ChatGPTApiService::class.java)
    }
}