package com.example.scanmodule.data.Api

import com.example.scanmodule.data.Api.model.LoginResponse
import com.example.scanmodule.data.Api.model.Login.LoginRequest
import com.example.scanmodule.data.Api.model.Motif.MotifResponse
import com.example.scanmodule.data.Api.model.ResponseResponsibilité
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Box2homeApi {

    @POST("loginCollaborateur")
    suspend fun loginCollaborateur(@Body loginRequest: LoginRequest): LoginResponse

    @GET("stock/getResponsibleList")
    suspend fun getResponsibleList (@Header("x-auth-token") token: String): ResponseResponsibilité
    @GET("stock/getLitigationStatusList")
    suspend fun getMotifList (@Header("x-auth-token") token: String): MotifResponse



}