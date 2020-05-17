package com.e.notesapp.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesAppService {

    @GET("/notas/")
    fun getAllNotas(): Call<ListadoNotasResponse>


}