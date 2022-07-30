package com.example.scanmodule.data.repository

import com.example.scanmodule.data.Api.Box2homeApi
import com.example.scanmodule.data.Api.model.LoginResponse
import com.example.scanmodule.data.Api.model.Login.LoginRequest
import javax.inject.Inject

class LoginRepository @Inject constructor(private val apiUserLogin: Box2homeApi){


    suspend fun loginRequest(loginRequest: LoginRequest): LoginResponse {
        return try {
            apiUserLogin.loginCollaborateur(loginRequest)
        } catch (e: Exception) {
            LoginResponse(400,"erreur",null)
        }
    }

}