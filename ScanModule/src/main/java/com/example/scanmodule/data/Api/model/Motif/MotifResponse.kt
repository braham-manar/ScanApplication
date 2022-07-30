package com.example.scanmodule.data.Api.model.Motif

import com.example.scanmodule.data.Api.model.Responsibility
import com.example.scanmodule.data.dataBase.model.MotifEntity
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.google.gson.annotations.SerializedName

data class MotifResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message : String,
    @SerializedName("response") val Response: List<Motif>?

)
data class Motif(
    @SerializedName("id") val id: Int,
    @SerializedName("code") val code : String,
    @SerializedName("label") val label : String

)
fun Motif.motifToMotifEntity(responseMotif : Motif) : MotifEntity {
    return MotifEntity(
        id = responseMotif.id,
        code = responseMotif.code,
        label = responseMotif.label

    )
}

