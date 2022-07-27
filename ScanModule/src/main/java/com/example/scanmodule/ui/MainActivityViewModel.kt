package com.example.scanmodule.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanmodule.data.Api.model.LoginRequest
import com.example.scanmodule.data.Api.model.LoginResponse
import com.example.scanmodule.data.Api.model.RequestResponsiblité
import com.example.scanmodule.data.Api.model.ResponseResponsibilité
import com.example.scanmodule.data.Respository.MainRespository
import com.example.scanmodule.data.dataBase.RoomRepository
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.example.scanmodule.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private val  mainRespository: MainRespository,private val repository: RoomRepository): ViewModel(){


    private val _loginLiveData = MutableLiveData<ResponseResponsibilité>()




    fun getResponsibilité(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            mainRespository.responsibilité(token)
        }


    }
    }