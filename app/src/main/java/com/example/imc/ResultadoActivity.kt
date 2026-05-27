package com.example.imc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {
    private lateinit var textNome: TextView
    private lateinit var textClassificacao: TextView
    private lateinit var textImc: TextView
    private lateinit var textPeso: TextView
    private lateinit var textAltura: TextView
    private lateinit var btnVoltar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado2)

        initViews()
        bindData()
    }

    private fun initViews() {
        textNome = findViewById(R.id.textNome)
        textClassificacao = findViewById(R.id.textClassificacao)
        textImc = findViewById(R.id.textImc)
        textPeso = findViewById(R.id.textPeso)
        textAltura = findViewById(R.id.textAltura)
        btnVoltar = findViewById(R.id.btnFecharResultados)
    }

    private fun bindData() {

        val imc = intent.getParcelableExtra<IMC>("value") ?: return

        textNome.text = imc.nome ?: "Sem nome"
        textClassificacao.text = imc.calcular()
        textImc.text = "Seu IMC %.2f".format(imc.imc)
        textPeso.text = "Seu Peso %.1f".format(imc.peso)
        textAltura.text = "Sua Altura %.1f".format(imc.altura)
        btnVoltar.setOnClickListener {
            finish()
        }

    }
}