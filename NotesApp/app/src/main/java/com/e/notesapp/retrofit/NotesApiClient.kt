package com.e.notesapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotesApiClient {

    private val NotesAppService: NotesAppService
    private val retrofit: Retrofit

    init {

        retrofit = Retrofit.Builder()
            .baseUrl("http://localhost:9000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        NotesAppService = retrofit.create(com.e.notesapp.retrofit.NotesAppService::class.java)
    }

    fun getNotesAppService() = NotesAppService
}