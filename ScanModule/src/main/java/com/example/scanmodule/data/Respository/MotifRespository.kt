package com.example.scanmodule.data.Respository

import com.example.scanmodule.data.Api.Box2homeApi
import com.example.scanmodule.data.Api.model.Motif.MotifResponse
import com.example.scanmodule.data.Api.model.Motif.motifToMotifEntity
import com.example.scanmodule.data.Api.model.ResponseResponsibilité
import com.example.scanmodule.data.dataBase.AppDAO
import javax.inject.Inject

class MotifRespository @Inject constructor(private val apiMotif: Box2homeApi,
    private val appDAO: AppDAO
    ){
        suspend fun motif( token: String): MotifResponse {
            return try {
                apiMotif.getMotifList(token).apply {
                    appDAO.inserMotif( this.Response?.map { motif ->
                        motif.motifToMotifEntity(motif)

                    })

                }
            } catch (e: Exception) {
                MotifResponse(400,"erreur",null)
            }
        }
}