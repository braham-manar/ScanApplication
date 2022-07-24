package com.example.scanmodule.data.Api.model

import com.google.gson.annotations.SerializedName


data class LoginResponse(
   @SerializedName("code")  val  code: Int,

   @SerializedName("message")  val  message: String,

   @SerializedName("Response") val Response: ResponseBody?

)

data class ResponseBody(
   @SerializedName("value") var value: String,
   @SerializedName("collaborateur") var collaborateur: Collaborateur? = null
)



