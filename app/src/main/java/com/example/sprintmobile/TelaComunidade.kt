package com.example.sprintmobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sprintmobile.databinding.ActivityTelaComunidadeBinding
import com.example.sprintmobile.model.Produto
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TelaComunidade : AppCompatActivity() {
    private val binding by lazy {
        ActivityTelaComunidadeBinding.inflate(layoutInflater)
    }

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnSalvar.setOnClickListener {
            salvarInfo()
        }
        binding.btnAtualizar.setOnClickListener {
            atualizarUsuario()
        }
        binding.btnListar.setOnClickListener {
            listarDados()
        }
    }

    private fun salvarInfo() {
        val dados = mapOf(
            "nomeProduto" to binding.editNomeProduto.text.toString(),
            "preco" to binding.editPreco.text.toString(),
            "loja" to binding.editLocal.text.toString(),
            "avaliacao" to binding.editAvaliacao.text.toString(),
            "idUsuario" to autenticacao.currentUser?.uid // Adiciona uma referência ao usuário
        )

        bancoDados
            .collection("produtos")
            .add(dados) // Usar 'add()' em vez de 'set()' para criar um novo documento com ID gerado automaticamente
            .addOnSuccessListener {
                AlertDialog.Builder(this)
                    .setTitle("SUCESSO AO CADASTRAR")
                    .setMessage("Registro salvo com sucesso.")
                    .setPositiveButton("Fechar") { dialog, posicao -> }
                    .create().show()
            }
            .addOnFailureListener {
                AlertDialog.Builder(this)
                    .setTitle("ERROR AO CADASTRAR REGISTRO")
                    .setMessage("Registro não salvo.")
                    .setPositiveButton("Fechar") { dialog, posicao -> }
                    .create().show()
            }
    }

    private fun atualizarUsuario() {

        val idUsuarioLogado = autenticacao.currentUser?.uid

        if (idUsuarioLogado != null) {
            bancoDados
                .collection("produtos")
                .document(idUsuarioLogado)
                .update("preco", binding.editPreco.text.toString())
                .addOnSuccessListener {
                    AlertDialog.Builder(this)
                        .setTitle("SUCESSO AO ATUALIZAR")
                        .setMessage("Registro atualizar com sucesso.")
                        .setPositiveButton("Fechar") { dialog, posicao -> }
                        .create().show()
                }
                .addOnFailureListener {
                    AlertDialog.Builder(this)
                        .setTitle("ERROR AO ATUALIZAR")
                        .setMessage("Registro não atualizado.")
                        .setPositiveButton("Fechar") { dialog, posicao -> }
                        .create().show()
                }

        }

    }

    private fun listarDados() {
        val idUsuarioLogado = autenticacao.currentUser?.uid
        if (idUsuarioLogado != null) {
            bancoDados
                .collection("produtos")
                .addSnapshotListener { querySnapshot, erro ->
                    if (erro != null) {
                        return@addSnapshotListener
                    }

                    val listaProdutos = mutableListOf<Produto>()
                    querySnapshot?.documents?.forEach { documentSnapshot ->
                        val produto = documentSnapshot.toObject(Produto::class.java)
                        produto?.let {
                            listaProdutos.add(it)
                        }
                    }

                    // Configure o RecyclerView
                    binding.rvProdutos.layoutManager = LinearLayoutManager(this)
                    binding.rvProdutos.adapter = ProdutoAdapter(listaProdutos) { produto ->
                        // Callback para deletar produto
                        deletarProduto(produto)
                    }
                }
        }
    }

    private fun deletarProduto(produto: Produto) {
        bancoDados
            .collection("produtos")
            .whereEqualTo("nomeProduto", produto.nomeProduto)
            .whereEqualTo("preco", produto.preco)
            .get()
            .addOnSuccessListener { querySnapshot ->
                querySnapshot.documents.forEach { document ->
                    document.reference.delete()
                }
            }
            .addOnFailureListener {
                AlertDialog.Builder(this)
                    .setTitle("Erro ao deletar")
                    .setMessage("Não foi possível deletar o produto.")
                    .setPositiveButton("Fechar") { dialog, _ -> }
                    .create().show()
            }
    }

}

