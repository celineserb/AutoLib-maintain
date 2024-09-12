package com.clovertech.autolib.views.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.clovertech.autolib.databinding.ActivityLoginBinding
import com.clovertech.autolib.model.Login
import com.clovertech.autolib.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(){

    private val MIN_PASSWD_LENGTH: Int = 3
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginButton = binding.loginButton

        loginButton.setOnClickListener {
            performLogin()
        }
    }


    private fun performLogin() {

        val userEmail: String = binding.userEmail.text.toString()
        val userPassword: String = binding.password.text.toString()
        loginViewModel.onLoginButtonClick(Login(userEmail, userPassword))

        if (userEmail.isEmpty()) {
            Toast.makeText(this, "Your email is invalid", Toast.LENGTH_SHORT).show()
        } else {
            if (userPassword.isEmpty()) {
                Toast.makeText(this, "Please provide a password", Toast.LENGTH_SHORT).show()
            } else {
                if (userPassword.length < MIN_PASSWD_LENGTH) {
                    Toast.makeText(this, "Your password is incorrect", Toast.LENGTH_SHORT).show()
                } else {
                    loginViewModel.loginResponse.observe(this,{ response ->
                        if (response.isSuccessful) {
                            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
                            val content = response.body()
                            if (content != null) {
                                saveToken(content.token, content.id)
                            }
                        } else {
                            Toast.makeText(this, response.message(), Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

    private fun saveToken(token: String, idUser: Int) {
        val prefs = getSharedPreferences("AUTOLIB_MAINTAIN", MODE_PRIVATE)
        prefs.edit{putString("AGENT_TOKEN",token)}
        loginViewModel.getAgent(idUser)
        loginViewModel.responseAgent.observe(this,{
            if(it.isSuccessful){
                prefs.edit{putInt("AGENT_ID",it.body()?.idAgent!!)}
                prefs.edit{putInt("USER_ID",idUser)}
            }
        })

        if (idUser != 0) {
            loginViewModel.getThisProfile(idUser)
            loginViewModel.responseProfile.observe(this,{
                if (it.isSuccessful) {
                    val profile = it.body()
                    if (profile != null) {
                        val name = "${profile.firstName} ${profile.lastName}"
                        prefs.edit { putString("AGENT_NAME", name) }
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


