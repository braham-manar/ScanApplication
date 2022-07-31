package com.example.scanmodule.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferencesUtil {

    val MY_PREFS_NAME = "scan_preferences"
    val TOKEN_KEY = "token_key"

    fun saveTokenToPreference(context: Context, token: String?) {
        val editor: SharedPreferences.Editor =
            context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()
        editor.putString(TOKEN_KEY, token)
        editor.apply()
    }

    fun getTokenFromPreference(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
        return prefs.getString(TOKEN_KEY, "token not found")?: ""
    }
}