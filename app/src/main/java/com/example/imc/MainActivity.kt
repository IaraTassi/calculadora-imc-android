package com.example.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.text.MessageFormat

class MainActivity : AppCompatActivity() {
    private lateinit var editNome: EditText
    private lateinit var editPeso: EditText
    private lateinit var editAltura: EditText
    private lateinit var btnCalc: Button
    private lateinit var layoutEditNome: TextInputLayout
    private lateinit var layoutEditPeso: TextInputLayout
    private lateinit var layoutEditAltura: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        editNome = findViewById(R.id.editNome)
        editPeso = findViewById(R.id.editPeso)
        editAltura = findViewById(R.id.editAltura)
        btnCalc = findViewById(R.id.btnCalc)

        layoutEditNome = findViewById(R.id.layoutEditNome)
        layoutEditPeso = findViewById(R.id.layoutEditPeso)
        layoutEditAltura = findViewById(R.id.layoutEditAltura)
    }

    private fun setupListeners() {

        btnCalc.setOnClickListener {
            if (validarCampos()) {
                calcularIMC()
            }
        }

        editNome.addTextChangedListener(clearError(layoutEditNome))
        editPeso.addTextChangedListener(clearError(layoutEditPeso))
        editAltura.addTextChangedListener(clearError(layoutEditAltura))
    }

    private fun calcularIMC() {

        val imc = IMC(
            editNome.text.toString().trim(),
            editPeso.text.toString().toFloat(),
            editAltura.text.toString().toFloat()
        )

        imc.calcular()

        val intent = Intent(this, ResultadoActivity::class.java)

        intent.putExtra("value", imc)

        startActivity(intent)
    }

    private fun validarCampos(): Boolean {

        val nome = editNome.text.toString().trim()

        val peso = editPeso.text.toString().toFloatOrNull()

        val altura = editAltura.text.toString().toFloatOrNull()

        if (nome.length < 3) {
            showError(editNome, layoutEditNome, "Nome inválido")
            return false
        }

        if (peso == null || peso <= 0f) {
            showError(editPeso, layoutEditPeso, "Peso inválido")
            return false
        }

        if (altura == null || altura <= 0f) {
            showError(editAltura, layoutEditAltura, "Altura inválida")
            return false
        }

        return true
    }

    private fun showError(
        editText: EditText,
        layout: TextInputLayout,
        message: String
    ) {
        layout.error = message
        editText.requestFocus()
    }

    private fun clearError(layout: TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                layout.error = null
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
    }
}