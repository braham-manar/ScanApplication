package com.example.scanmodule.ui.scan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scanmodule.dataBase.RoomRepository
import com.example.scanmodule.dataBase.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(private val repository: RoomRepository):ViewModel(){


    lateinit var userData:MutableLiveData<List<UserEntity>>
    init{
        userData= MutableLiveData()
        loadRecords()

    }
    fun getRecordsObserver():MutableLiveData<List<UserEntity>>{
        return userData

    }
    fun loadRecords(){
        val list=repository.getRecords()
        userData.postValue(list)
    }
    fun insertRecords(userEntity: UserEntity){
        repository.insertRecord(userEntity)
        loadRecords()
    }
}