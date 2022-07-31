package com.example.scanmodule.util

import android.app.Activity
import android.content.Context

fun getTokenFromPref(activity : Activity) : String{
    val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return ""
    return sharedPref.getString("token_key" , "")?: ""
}