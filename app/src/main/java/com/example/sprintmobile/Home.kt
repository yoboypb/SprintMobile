package com.example.sprintmobile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Home : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Configurar o botão para abrir a Activity
        val imageView: ImageView = view.findViewById(R.id.redirectFone)
        imageView.setOnClickListener {
            // Criar um Intent para abrir a nova Activity
            val intent = Intent(activity, TelaProduto::class.java)
            startActivity(intent)
        }

        return view
    }
}
