package com.e.notesapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.e.notesapp.common.Constantes
import com.e.notesapp.dto.LoginDto
import com.e.notesapp.retrofit.SharedPreferencesManager
import com.e.notesapp.viewmodel.UserViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var loginDto: LoginDto


    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        SharedPreferencesManager.setStringValue(Constantes.TOKEN, null)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.buttonLogin)

        btnLogin.setOnClickListener(View.OnClickListener {
            loginDto = LoginDto(
                username.text.toString(),
                password.text.toString()
            )
            userViewModel.doLogin(loginDto).observe(this, Observer {
                SharedPreferencesManager.setStringValue(Constantes.TOKEN, it.token)
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            })
        })

         }
}
