package com.clovertech.autolib.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login_agent.*

class LoginActivity : AppCompatActivity(){

    private val MIN_PASSWD_LENGTH: Int = 3
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_agent)
        val loginButton = findViewById<Button>(R.id.login_button)

        loginButton.setOnClickListener {
            performLogin()
        }
    }


    private fun performLogin() {

        val userEmail: String = numChasis.text.toString()
        val userPassword: String = password.text.toString()
        loginViewModel.onLoginButtonClick(Login(userEmail, userPassword))

        if (userEmail.isEmpty()) {
            Toast.makeText(this, "Votre Email est invalid", Toast.LENGTH_SHORT).show()
        } else {
            if (userPassword.isEmpty()) {
                Toast.makeText(this, "Entrer le mot de passe", Toast.LENGTH_SHORT).show()
            } else {
                if (userPassword.length < MIN_PASSWD_LENGTH) {
                    Toast.makeText(this, "Mot de passe incorrect", Toast.LENGTH_SHORT).show()
                } else {

                    loginViewModel.loginResponse.observe(this,{ response ->

                        if (response.isSuccessful) {
                            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                            val content = response.body()
                            if (content != null) {
                                saveToken(content.token, content.id)
                            }
                        } else {
                            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

    private fun saveToken(token: String, idUser: Int) {

        PrefUtils.with(this).save(PrefUtils.Keys.TOKEN, token)
        PrefUtils.with(this).save(PrefUtils.Keys.ID, idUser)

        if (idUser != 0) {
            loginViewModel.getThisProfile(idUser)
            loginViewModel.responseProfile.observe(this,{

                if (it.isSuccessful) {

                    val profile = it.body()
                    if (profile != null) {
                        val name = "${profile.firstName} ${profile.lastName}"
                        PrefUtils.with(this).save(PrefUtils.Keys.AGENT_NAME, name)
                        Toast.makeText(this, "Welcome $name", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }

                } else {
                    Toast.makeText(this, "Login error, Please try again", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

}


