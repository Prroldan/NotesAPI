package com.e.notesapp.retrofit

import com.e.notesapp.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class NotesAppInterceptor: Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        var token =
            SharedPreferencesManager.getStringValue(
                Constantes.TOKEN
            )
        var request = chain.request()

        token= SharedPreferencesManager.getStringValue(Constantes.TOKEN)
        request = request?.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()


        return chain.proceed(request)

    }
}