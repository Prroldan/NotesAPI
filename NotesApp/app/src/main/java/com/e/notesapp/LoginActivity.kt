package com.e.notesapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.e.notesapp.common.MyApp
import com.e.notesapp.retrofit.UserRequest
import com.e.notesapp.viewmodel.UserViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject lateinit var userViewmodel: UserViewModel
    @Inject lateinit var sharedPref: SharedPreferences


    private val builder = Retrofit.Builder()
        .baseUrl("http://localhost:9000")
        .addConverterFactory(GsonConverterFactory.create())
    var retrofit = builder.build()

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnLogin = findViewById(R.id.buttonLogin)
        (applicationContext as MyApp).appComponent.inject(this)


        btnLogin.setOnClickListener(View.OnClickListener {
            val request =
                UserRequest(username.text.toString(), password.text.toString())

            userViewmodel.doLogin(request)

        })
    }







}
