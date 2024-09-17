package com.example.sprintmobile.API

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintmobile.R

class ChatAdapter(private val messageList: List<Message>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message = messageList[position]
        holder.textViewMessage.text = message.content

        if (message.isUser) {
            holder.textViewMessage.setBackgroundResource(R.drawable.edittext_background)
        } else {
            holder.textViewMessage.setBackgroundResource(R.drawable.response_text)
        }
    }

    override fun getItemCount(): Int = messageList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewMessage: TextView = itemView.findViewById(R.id.textViewMessage)
    }
}
