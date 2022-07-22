package com.example.scanmodule.data.Api.model

import com.google.gson.annotations.SerializedName


data class Collaborateur(
    @SerializedName("id") var id: Int,
    @SerializedName("lastname") var lastname: String? = null,
    @SerializedName("firstname") var firstname: String? = null,
    @SerializedName("birthDay") var birthDay: String? = null,
    @SerializedName("phone") var phone: String? = null,
)