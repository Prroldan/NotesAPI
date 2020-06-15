package com.e.notesapp.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.e.notesapp.common.Constantes
import com.e.notesapp.common.MyApp


class SharedPreferencesManager private constructor(ctx: Context) {
    private val ctx: Context? = null

    companion object {
        private val sharedPreferences: SharedPreferences?
            private get() = MyApp.context?.getSharedPreferences(
                Constantes.APP_SETTINGS,
                Context.MODE_PRIVATE
            )

        fun setStringValue(dataLabel: String?, dataValue: String?) {
            val editor =
                sharedPreferences?.edit()
            editor?.putString(dataLabel, dataValue)
            editor?.commit()
        }

        fun getStringValue(dataLabel: String?): String? {
            return sharedPreferences
                ?.getString(dataLabel, null)
        }
    }
}