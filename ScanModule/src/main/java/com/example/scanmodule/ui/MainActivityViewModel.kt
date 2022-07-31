package com.example.scanmodule.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanmodule.data.Api.model.ResponseResponsibilité
import com.example.scanmodule.data.Respository.MainRespository
import com.example.scanmodule.data.Respository.MotifRespository
import com.example.scanmodule.data.dataBase.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(
    private val  mainRespository: MainRespository,private val motifRespository: MotifRespository): ViewModel(){


    //private val _loginLiveData = MutableLiveData<ResponseResponsibilité>()




    fun getResponsibilité(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            mainRespository.responsibilité(token)
        }
    }
    fun getMotif(token: String){
        viewModelScope.launch(Dispatchers.IO) {
            motifRespository.motif(token)

        }
    }

    }