package com.tavares.kaique.hungrypizza

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalhe_pedido.*

class DetalhePedidoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_pedido)

        //Pegando o meu objeto passado da PedidoActivity
        //Armazeno em uma variavel e pego o Parcelable pois é um objeto e passo seu nome
        val pedido = intent.getParcelableExtra<Pedido>("pedido")

        //Pego meus textView e coloco dentro do parametro text o valor que eu conseguir extrair do objeto.
        tvNomeCliente.text= pedido.nomeCliente
        tvTamanho.text= pedido.tamano
        tvSabores.text= pedido.sabores.toString()
        tvPagamento.text=pedido.tipoPagamento
    }
}
