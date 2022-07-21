package com.example.scanmodule.data.Api

import com.example.scanmodule.data.dataBase.model.LoginUserEntity
import com.example.scanmodule.data.remote.model.LoginResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiUserLogin {
    @GET("posts")
    fun getMail(): Call<List<LoginResponse>>
}