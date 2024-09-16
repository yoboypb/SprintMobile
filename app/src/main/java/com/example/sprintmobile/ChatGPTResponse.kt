package com.example.sprintmobile
data class ChatGPTResponse(
    val choices: List<Choice>
) {
    data class Choice(
        val message: Message
    ) {
        data class Message(
            val role: String,
            val content: String
        )
    }
}