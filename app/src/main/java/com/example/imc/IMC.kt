package com.example.imc

import android.os.Parcel
import android.os.Parcelable

class IMC(var nome: String?, var peso: Float, var altura: Float, var imc: Float) :
    Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    constructor(name: String, peso: Float, altura: Float) : this(
        name,
        peso,
        altura,
        0.0f
    )

    fun calcular(): String {

        val alt = altura / 100
        val calc = (peso / (alt * alt)).toFloat()

        val msg = when (calc) {
            in 0f..16f -> "Magreza grave"
            in 16f..17f -> "Magreza moderada"
            in 17f..19f -> "Magreza leve"
            in 19f..25f -> "Saudável"
            in 25f..30f -> "Sobrepeso"
            in 30f..35f -> "Obesidade I"
            in 35f..40f -> "Obesidade II"
            else -> "Obesidade mórbida"
        }

        imc = calc
        return msg
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nome)
        parcel.writeFloat(peso)
        parcel.writeFloat(altura)
        parcel.writeFloat(imc)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IMC> {
        override fun createFromParcel(parcel: Parcel): IMC {
            return IMC(parcel)
        }

        override fun newArray(size: Int): Array<IMC?> {
            return arrayOfNulls(size)
        }
    }
}