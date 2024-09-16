package com.example.sprintmobile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import androidx.appcompat.widget.SearchView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Search : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyAdapter
    private lateinit var itemList: MutableList<String>
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Inicializa a lista de itens
        itemList = mutableListOf()
        populateItemList() // Preenche a lista com dados

        // Configura o RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MyAdapter(itemList)
        recyclerView.adapter = adapter

        // Configura o SearchView
        searchView = view.findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Chama o método de filtragem do adaptador
                adapter.filter(newText ?: "")
                return false
            }
        })

        return view
    }

    private fun populateItemList() {
        // Adiciona itens à lista
        itemList.apply {
            add("Apple")
            add("Banana")
            add("Orange")
            add("Grape")
            add("Pineapple")
            add("Watermelon")
            add("Strawberry")
            add("Cherry")
            add("Mango")
            add("Blueberry")
        }
    }
}