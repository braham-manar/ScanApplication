package com.example.scanmodule.data.Api.model

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    @SerializedName("login") var login: String,
    @SerializedName("password") var password: String
)