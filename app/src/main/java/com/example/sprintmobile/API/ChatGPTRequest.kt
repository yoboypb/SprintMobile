package com.example.sprintmobile.API

data class ChatGPTRequest(
    val model: String,
    val messages: List<Message>,
    val max_tokens: Int
) {
    data class Message(
        val role: String,
        val content: String
    )
}
