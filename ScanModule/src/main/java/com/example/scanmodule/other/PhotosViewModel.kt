package com.example.scanmodule.other

import androidx.lifecycle.ViewModel
import com.example.scanmodule.data.dataBase.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val repository: RoomRepository): ViewModel(){

    val responsibilityDataLiveData  = repository.getRecordsRespnsabiliteAsLiveData()

}