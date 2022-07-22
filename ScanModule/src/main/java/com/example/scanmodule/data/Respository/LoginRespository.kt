package com.example.scanmodule.data.repository

import com.example.scanmodule.data.Api.ApiUserLogin
import com.example.scanmodule.data.Api.model.LoginResponse
import com.example.scanmodule.data.Api.model.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiUserLogin: ApiUserLogin){


    suspend fun loginRequest(loginRequest: LoginRequest): LoginResponse {
        return try {
            apiUserLogin.loginCollaborateur(loginRequest)
        } catch (e: Exception) {
            LoginResponse(400,"erreur",null)
        }
    }

}