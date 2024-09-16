package com.example.sprintmobile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Chat : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var messageList: MutableList<Message>
    private lateinit var editTextMessage: EditText
    private lateinit var buttonSend: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        // Inicializar lista de mensagens e o adaptador
        messageList = mutableListOf()
        chatAdapter = ChatAdapter(messageList)

        // Configurar RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewChat)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = chatAdapter

        // Configurar campo de texto e botão de envio
        editTextMessage = view.findViewById(R.id.editTextMessage)
        buttonSend = view.findViewById(R.id.buttonSend)

        buttonSend.setOnClickListener {
            val userMessage = editTextMessage.text.toString()
            try{
                if (userMessage.isNotEmpty()) {
                    // Adicionar mensagem do usuário
                    messageList.add(Message(userMessage, true))
                    chatAdapter.notifyDataSetChanged()

                    // Limpar o campo de texto
                    editTextMessage.setText("")

                    // Enviar mensagem para a API
                    sendMessageToChatGPT(userMessage)

                    Log.i("Teste",("if"))
            }

            }catch (e: Exception){
                e.printStackTrace()
            }


        }

        return view
    }

    private fun sendMessageToChatGPT(userMessage: String) {
        val message = ChatGPTRequest.Message("user", userMessage)
        val request = ChatGPTRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(message),
            max_tokens = 100
        )

        val apiService = RetrofitHelper.getChatGPTApiService()
        val call = apiService.sendMessage(request)

        call.enqueue(object : retrofit2.Callback<ChatGPTResponse> {
            override fun onResponse(call: retrofit2.Call<ChatGPTResponse>, response: retrofit2.Response<ChatGPTResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val chatResponse = response.body()!!.choices[0].message.content
                    messageList.add(Message(chatResponse, false))
                    chatAdapter.notifyDataSetChanged()
                    Log.i("Teste", "Resposta recebida")
                } else {
                    Log.e("Teste", "Erro na resposta: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<ChatGPTResponse>, t: Throwable) {
                Log.e("Teste", "Falha na requisição", t)
            }
        })
    }
}
