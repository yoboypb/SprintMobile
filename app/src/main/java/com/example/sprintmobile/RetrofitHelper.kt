package com.example.sprintmobile

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    companion object {
        private const val BASE_URL = "https://api.openai.com/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getChatGPTApiService(): ChatGPTApiService {
            return retrofit.create(ChatGPTApiService::class.java)
        }
    }
}