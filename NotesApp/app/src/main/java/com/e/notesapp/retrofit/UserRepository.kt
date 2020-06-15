package com.e.notesapp.retrofit


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.e.notesapp.common.MyApp
import com.e.notesapp.dto.LoginDto
import com.e.notesapp.retrofit.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    var notesAppService: NotesAppService
    var notesApiClient: NotesApiClient =
        NotesApiClient()
    var loginResponse:MutableLiveData<LoginResponse> = MutableLiveData<LoginResponse>()



    init {
        notesAppService = notesApiClient.getNotesAppService()
    }

    fun login(loginDto: LoginDto): MutableLiveData<LoginResponse> {
        val call: Call<LoginResponse> = notesAppService.login(loginDto)
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.isSuccessful) {
                    loginResponse.value = response.body()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(MyApp.context, "Error al iniciar sesión", Toast.LENGTH_LONG).show()
            }
        })

        return loginResponse
    }
}