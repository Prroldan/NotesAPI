package com.e.notesapp.retrofit

import com.e.notesapp.dto.CreateNotaDto
import com.e.notesapp.dto.LoginDto
import com.e.notesapp.dto.NotaDto
import com.e.notesapp.modelos.DetalleNotaResponse
import com.e.notesapp.modelos.ListadoNotaResponseItem
import com.e.notesapp.retrofit.response.ListadoNotasResponse
import com.e.notesapp.retrofit.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface NotesAppService {

    @GET("/notas/")
    fun getAllNotas(): Call<List<ListadoNotaResponseItem>>

    @POST("/auth/login")
    fun login(@Body loginDto: LoginDto): Call<LoginResponse>

    @GET("/notas/{id}")
    fun getNotasById(@Path("id")idNota:String): Call<DetalleNotaResponse>

    @POST("/notas/")
    fun createNota(@Body createNotaDto: CreateNotaDto): Call<NotaDto>

    @DELETE("/notas/{id}")
    fun deleteNota(@Path("id")idNota:String): Call<Void>

    @PUT("/notas/{id}")
    fun editNota(@Path("id")idNota:String,@Body createNotaDto: CreateNotaDto): Call<NotaDto>


}