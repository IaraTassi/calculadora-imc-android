package com.example.imc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ImcCalculatorTest {
    @Test
    fun deve_calcular_imc_e_retornar_categoria_correta() {
        val imc = IMC("Maria", 70f, 170f)

        val resultado = imc.calcular()

        assertEquals("Saudável", resultado)
    }


    @Test
    fun deve_ser_calculado_imc_corretamente() {
        val imc = IMC("Maria", 70f, 170f)

        imc.calcular()

        assertEquals(24.22f, imc.imc, 0.01f)
    }

}