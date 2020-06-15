package com.e.notesapp.retrofit

import android.provider.SyncStateContract
import com.e.notesapp.common.Constantes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NotesApiClient {
    private val notesAppService: NotesAppService
    private val retrofit: Retrofit

    init {

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(NotesAppInterceptor())

        var logging : HttpLoggingInterceptor = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val cliente = okHttpClientBuilder.addInterceptor(logging).build()


        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()



        notesAppService = retrofit.create(NotesAppService::class.java)
    }

    fun getNotesAppService() = notesAppService

}