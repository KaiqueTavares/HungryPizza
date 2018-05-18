package com.tavares.kaique.hungrypizza

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnConcectar.setOnClickListener {
            logar()
        }
    }

    //Clique do Botão
    fun logar (){

        //Recupera os valores digitados na tela e coloca para dentro de uma variavel
        val login = etLogin.text.toString()
        val senha = etSenha.text.toString()

        //Valida o usuário e senha
        //Se minha variavel login for igual a Fiap e senha a 123
        if (login == "Fiap" && senha == "123"){
            //Posso ir para a proxima tela pois o usuario está autenticado.
            val telaPedido = Intent(this,PedidoActivity::class.java)
            //Vou passar os valores de minhas variaveis para a outra tela, aqui há 2 formas:
            //Tenho o jeito de armazenar um valor dentro desta variavel sem ter criado uma
            //Variavel para pegar o que eu tinha dentro dos meus editable text
            //telaPedido.putExtra("usuario",etLogin.editableText.toString())

            //Passando um nome de como vou recuperar na outra tela e minha variavel atribuida
            telaPedido.putExtra("usuario",login)

            //Vou iniciar minha Activity
            startActivity(telaPedido)
            finish()
            //Se ele não digitou corretamente os dados ele esta invalido para entrar no sistema
        }else{
            //Login e senha invalidos
            Toast.makeText(this,"Usuário ou senha Inválidos",Toast.LENGTH_SHORT).show()
        }

    }
}
