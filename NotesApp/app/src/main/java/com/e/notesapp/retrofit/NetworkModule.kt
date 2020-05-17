package com.e.notesapp.retrofit;


import android.content.Context
import android.content.SharedPreferences
import com.e.notesapp.common.Constantes
import com.e.notesapp.common.MyApp
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl():String = Constantes.BASE_URL

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        val sharedPref = MyApp.instance?.getSharedPreferences(
            Constantes.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        return sharedPref
    }

    @Singleton
    @Provides
    fun provideNotesAppRetrofitService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): NotesAppService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NotesAppService::class.java)
    }

    @Singleton
    @Provides
    fun provideUserService(@Named("url") baseUrl: String, okHttpClient: OkHttpClient): UserService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(UserService::class.java)
    }



}
