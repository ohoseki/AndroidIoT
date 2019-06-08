package br.com.rafaelohoseki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import android.widget.Toast




class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        if (usuarioLogado()){
            proximaTelaLogada()
        }

        botaoEntrar.setOnClickListener {
            validarUsuario()
        }

    }
    private fun usuarioLogado(): Boolean{
        return mAuth.currentUser !== null
    }
    private fun proximaTelaLogada(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun validarUsuario(){
        val email = inputEmail.editText?.text.toString()
        val senha = inputSenha.editText?.text.toString()
        if(email.isEmpty()) {
            inputEmail.error = "Favor Preencher o e-mail"
            inputEmail.isErrorEnabled = true
            return
        } else {
            inputEmail.isErrorEnabled = false
        }

        if(senha.isEmpty()) {
            inputSenha.error = "Favor Preencher a senha"
            inputSenha.isErrorEnabled = true
            return
        } else {
            inputSenha.isErrorEnabled = false
        }


        mAuth.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    proximaTelaLogada()
                } else {
                    Toast.makeText(this, "Falha na autenticacao",
                        Toast.LENGTH_LONG).show()
                }

            }
    }


}
