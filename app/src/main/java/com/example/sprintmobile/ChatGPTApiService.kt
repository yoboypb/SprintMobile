package com.example.sprintmobile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatGPTApiService {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sk-ixHEtMKjQYPUCmhLlmjZG08Jtds4BMgPdP8YOxw_3XT3BlbkFJM5PGK-qZeJpKWYS_ojQJjhWMZWqzDqg3nfQ3njmuQA" // Substitua com sua chave de API
    )
    @POST("v1/chat/completions")
    fun sendMessage(@Body request: ChatGPTRequest): Call<ChatGPTResponse>
}