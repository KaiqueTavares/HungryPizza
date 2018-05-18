package com.tavares.kaique.hungrypizza

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by kaiqu on 18/05/2018.
 */

data class  Pedido (val nomeCliente:String,
                    val sabores:List<String>,
                    val tamano:String,
                    val tipoPagamento:String):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nomeCliente)
        parcel.writeStringList(sabores)
        parcel.writeString(tamano)
        parcel.writeString(tipoPagamento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pedido> {
        override fun createFromParcel(parcel: Parcel): Pedido {
            return Pedido(parcel)
        }

        override fun newArray(size: Int): Array<Pedido?> {
            return arrayOfNulls(size)
        }
    }

}