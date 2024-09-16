package com.example.sprintmobile

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ChatGPTApiService {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sk-YY_Tx5M_jYl3zK8ZcIrGpv_yiNxXBJ5Iq2ByDfoS3gT3BlbkFJSNIm7Icn2ekYNaiiUJSoh_AORihs5x2nw8HtQO44AA" // Substitua com sua chave de API
    )
    @POST("v1/chat/completions")
    fun sendMessage(@Body request: ChatGPTRequest): Call<ChatGPTResponse>
}