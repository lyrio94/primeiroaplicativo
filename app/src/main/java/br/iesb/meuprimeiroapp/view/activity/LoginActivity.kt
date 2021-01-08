package br.iesb.meuprimeiroapp.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.iesb.meuprimeiroapp.R
import br.iesb.meuprimeiroapp.domain.LoginData
import br.iesb.meuprimeiroapp.domain.LoginResult
import br.iesb.meuprimeiroapp.domain.RegisterData
import br.iesb.meuprimeiroapp.domain.RegisterResult
import br.iesb.meuprimeiroapp.viewmodel.LoginViewModel
import br.iesb.meuprimeiroapp.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    lateinit var viewmodel: LoginViewModel
    lateinit var viewmodelRegister: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener { login() }
        tvForgotPassword.setOnClickListener { forgotPassword() }
        tvRegister.setOnClickListener { register() }

        viewmodel = LoginViewModel(application)
        viewmodel.resultadoParaTela.observe(this) { resultado ->
            processarResultLogin(resultado)
        }
    }


    fun processarResultLogin(res: LoginResult){
        //mensagem de erro para usuario atraves de Toast
        if(res.error != null) {
            Toast.makeText(this, res.error, Toast.LENGTH_LONG).show()
            return
        }

        val intentHome = Intent(this, HomeActivity::class.java)
        startActivity(intentHome)
        finish()
    }

    fun login() {

        val email = etEmail.text.toString()
        val pass = etPassword.text.toString()

        val data = LoginData(email, pass)
        viewmodel.login(data)
    }

    fun forgotPassword() {
        val intetForgotPassword = Intent(this, ForgotPasswordActivity::class.java);
        startActivity(intetForgotPassword);
    }

    fun register() {

        val intentRegister = Intent(this, RegisterActivity::class.java)
        startActivity(intentRegister)

    }
}