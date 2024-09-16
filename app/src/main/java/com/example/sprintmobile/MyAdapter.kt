package com.example.sprintmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val itemList: MutableList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val itemListFull: List<String> = ArrayList(itemList) // Copia a lista original

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Infla o layout personalizado do item da lista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itens_lista, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Obtém o item atual da lista e configura o TextView
        val currentItem = itemList[position]
        holder.textView.text = currentItem
    }

    override fun getItemCount(): Int = itemList.size

    // Método de filtragem
    fun filter(text: String) {
        itemList.clear()
        if (text.isEmpty()) {
            itemList.addAll(itemListFull) // Restaura a lista completa se a pesquisa estiver vazia
        } else {
            val searchText = text.toLowerCase()
            for (item in itemListFull) {
                if (item.toLowerCase().contains(searchText)) {
                    itemList.add(item) // Adiciona os itens que correspondem à pesquisa
                }
            }
        }
        notifyDataSetChanged() // Atualiza a lista exibida
    }

    // ViewHolder personalizado para armazenar as referências aos elementos do layout
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.itemTextView)
    }
}
