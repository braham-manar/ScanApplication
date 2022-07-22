package com.example.scanmodule.data.Api

import com.example.scanmodule.data.Api.model.LoginResponse
import com.example.scanmodule.data.Api.model.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUserLogin {

    @POST("loginCollaborateur")
    suspend fun loginCollaborateur(@Body loginRequest: LoginRequest): LoginResponse
}