package com.dmytro.mvvmtemplate.common.data

import android.content.Context
import android.preference.PreferenceManager

class TokenLocalStorage(private val context: Context) : TokenStorage {

    val TOKEN = "token"

    override fun saveToken(token: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putString(TOKEN, token)
        editor.apply()
    }

    override fun getToken(): String = PreferenceManager.getDefaultSharedPreferences(context).getString(TOKEN, "")
}