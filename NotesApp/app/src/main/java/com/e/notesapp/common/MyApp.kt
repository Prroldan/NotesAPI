package com.e.notesapp.common

import android.app.Application
import android.content.Context

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: MyApp? = null
        val context: Context?
            get() = instance
    }
}