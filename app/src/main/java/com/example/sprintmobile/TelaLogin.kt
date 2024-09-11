package com.example.sprintmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TelaLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tela_login)
        var btnEntrar: Button
        btnEntrar = findViewById(R.id.btnEntrar)

        btnEntrar.setOnClickListener{
            var navegarTelaInicial = Intent(this,TelaInicial::class.java)
            startActivity(navegarTelaInicial)
        }
    }
}