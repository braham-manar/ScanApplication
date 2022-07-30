package com.example.scanmodule.data.Respository

import com.example.scanmodule.data.Api.Box2homeApi
import com.example.scanmodule.data.Api.model.ResponseResponsibilité
import com.example.scanmodule.data.Api.model.responsibilityToResponsabilityEntity

import com.example.scanmodule.data.dataBase.AppDAO

import javax.inject.Inject

class MainRespository @Inject constructor(
    private val apiResponsibilité: Box2homeApi,
    private val appDAO: AppDAO
){
    suspend fun responsibilité( token: String): ResponseResponsibilité {
        return try {
            apiResponsibilité.getResponsibleList(token).apply {
                appDAO.inserResponsabilite( this.Response?.map { responsability ->
                    responsability.responsibilityToResponsabilityEntity(responsability)

                })
              //  Log.i("tokenrespon", "res:"+responsibilité(token))
            }
        } catch (e: Exception) {
            ResponseResponsibilité(400,"erreur",null)
        }
    }

}