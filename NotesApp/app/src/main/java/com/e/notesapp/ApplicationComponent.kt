package com.e.notesapp

import com.e.notesapp.retrofit.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(loginActivity: LoginActivity)


}