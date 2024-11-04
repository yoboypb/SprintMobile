package com.example.sprintmobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sprintmobile.databinding.ProdutoListaBinding
import com.example.sprintmobile.model.Produto

class ProdutoAdapter(
    private val produtos: MutableList<Produto>,
    private val onDeleteClicked: (Produto) -> Unit // Callback para deletar
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(val binding: ProdutoListaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = ProdutoListaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.binding.textNomeProduto.text = produto.nomeProduto
        holder.binding.textPrecoProduto.text = produto.preco.toString() // Converte para String se necessário
        holder.binding.textLojaProduto.text = produto.loja
        holder.binding.textAvaliacaoProduto.text = produto.avalicao

        // Configura o clique no ícone de lixeira
        holder.binding.iconDelete.setOnClickListener {
            onDeleteClicked(produto) // Chama o callback para deletar
        }
    }

    override fun getItemCount(): Int = produtos.size

    // Método opcional para atualizar os dados no adapter
    fun updateData(novosProdutos: List<Produto>) {
        produtos.clear()
        produtos.addAll(novosProdutos)
        notifyDataSetChanged()
    }
}
