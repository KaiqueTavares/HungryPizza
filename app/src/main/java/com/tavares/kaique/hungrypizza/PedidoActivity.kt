package com.tavares.kaique.hungrypizza

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_pedido.*

class PedidoActivity : AppCompatActivity() {

    val pizzasSelecionadas = ArrayList<String>()
    var tamanhoSelecionado = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        //Recuperando o valor informado na tela de login
        val bundle = intent.extras
        val usuario = bundle.getString("usuario")
        //Segunda Maneira para recuperar valores seria:
        // val usuario = intent?.getStringExtra("usuario")!!.toString()

        //Alterando o texto de boas vindas
        tvUsuario.text = "Olá $usuario"
        btCalcular.setOnClickListener {
            calcular()
        }
    }

    //Clique do botão
    fun calcular(){
        //Recuperar o ID do RadioButtonSelecionado
        //Esse rgTamanhoPizza é um ID que eu dei para o meu RadioGroup
        //Lembre-se que coloquei este como valor imutavel, uma constante que nunca muda
        val idSelecionado = rgTamanhoPizza.checkedRadioButtonId

        //valorTamnhoPizza é o valor da pizza
        var valorTamanhoPizza=0
        //Valor da entrega
        var valor=0.0

        //Quando o meu seletor for igual há:
        when(idSelecionado){
            //Se eu selecionei o tamanho pequeno
            R.id.rbPequena->{
                //o valor vai ser 10
               valorTamanhoPizza = 10
                //E vou setar  minha variavel tamanhoSeleciona- para o texto que estiver dentro do id
                //Para que eu possa mostrar em outra tela
                tamanhoSelecionado=rbPequena.text.toString()
            }
            R.id.rbMedia -> {
                valorTamanhoPizza = 12
                tamanhoSelecionado = rbMedia.text.toString()
            }
            R.id.rbGrande -> {
                valorTamanhoPizza = 15
                tamanhoSelecionado = rbGrande.text.toString()
            }
        }

        //Verificando o sabor da pizza

        //isChecked é responsavel por verificar se eu selecionei ou não o sabor
        //Se o checkbox Atum estiver selecionado
        if(cbAtum.isChecked){
            //pego minha variavel de valor para dar o valor final da pizza
            //Aqui estou falando que o valor sera somado a 3 mais o valor da variavel já
            //Setada anteriormente e converto para double, pois pode dar numero quebrado
            //Em uma situação real e como vou dar o feedback com R$00,00
            valor +=(3+valorTamanhoPizza).toDouble()
            //Como posso selecionar mais de uma pizza com sabor diferente
            //Pego minha variavel que é um ArrayList <e tenho que passar a string>
            //Como tenho que passar a string eu coloco .add(para adiocionar)
            //Pego o que esta Escrito no ID cbAtum e converto para string.
            pizzasSelecionadas.add(cbAtum.text.toString())
        }

        if (cbBacon.isChecked) {
            valor += (5 + valorTamanhoPizza).toDouble()
            pizzasSelecionadas.add(cbBacon.text.toString())
        }

        if (cbCalabresa.isChecked) {
            valor += (4 + valorTamanhoPizza).toDouble()
            pizzasSelecionadas.add(cbCalabresa.text.toString())
        }

        if (cbMussarela.isChecked) {
            valor += (2 + valorTamanhoPizza).toDouble()
            pizzasSelecionadas.add(cbMussarela.text.toString())
        }

        //Vou recuperar agora o pagamento que o usuario selecionou e vou guardar em uma variavel
        val pagamento = spPagamentos.selectedItem as String

        //Exibir a confirmação do pedido em um modal
        //Vou criar uma variavel de alerta e vou montar um Alert
        val alert = AlertDialog.Builder(this)
        //Vou setar o titulo
        alert.setTitle("Confirmação")
        //Estou setando a mensagem
        //\n quebro a linha BR DO HTML
        alert.setMessage("Valor: $valor\nPagamento: $pagamento")

        //Estou setando um botão de sim
        //Ele vai abrir um dialogo de interface para eu ter um debugLog
        alert.setPositiveButton("SIM", DialogInterface.OnClickListener { dialog, which ->
            /*Log.i("Pedido", "Confirmação de Pedido")
            Toast.makeText(this@PedidoActivity, "Pedido Confirmado!",
                    Toast.LENGTH_SHORT).show()*/

            //Se ele confirmou posso passar os dados para a outra tela
            val telaDetalhePedido = Intent(this, DetalhePedidoActivity::class.java)
            //Vou passar um objeto para a proxima tela, logo tenho que preencher seus atributos
            val pedido = Pedido(etNomeCliente.text.toString(),
                    pizzasSelecionadas,
                    tamanhoSelecionado,
                    spPagamentos.selectedItem.toString())
            //Aqui estou passando meu objeto para a proxima tela
            telaDetalhePedido.putExtra("pedido", pedido)
            //Inicio minha Activity
            startActivity(telaDetalhePedido)
        })
        //Aqui estou setando o botão não que se ele clicar não acontece nada
        alert.setNegativeButton("NÃO", null)
        //Estou apresentando meu Alert
        alert.show()
    }

}
