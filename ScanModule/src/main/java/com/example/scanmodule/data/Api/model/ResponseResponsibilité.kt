package com.example.scanmodule.data.Api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.google.gson.annotations.SerializedName

data class ResponseResponsibilité(

    @SerializedName("code") val code: Int,
    @SerializedName("message") val message : String,
    @SerializedName("response") val Response: List<Responsibility>?

)


data class Responsibility(

    @SerializedName("id") var id:Int,
    @SerializedName("code") var code:String,
    @SerializedName("label") var label:String

)
fun Responsibility.responsibilityToResponsabilityEntity(responseResponsibilité : Responsibility) : ResponsabilityEntity {
    return ResponsabilityEntity(
        id = responseResponsibilité.id,
        code = responseResponsibilité.code,
        label = responseResponsibilité.label

    )}






