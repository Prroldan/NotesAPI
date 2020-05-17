package com.e.notesapp.common

import android.app.Application
import com.e.notesapp.ApplicationComponent

class MyApp: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}