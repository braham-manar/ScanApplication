package com.example.scanmodule.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanmodule.dataBase.CodeScanEntity
import com.example.scanmodule.dataBase.RoomRepository
import com.example.scanmodule.dataBase.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(private val repository: RoomRepository):ViewModel(){

    val scanDataLiveData = repository.getRecordsCodeScanAsLiveData()

    fun insertRecordCodeScan(codeScanEntity: CodeScanEntity){
        viewModelScope.launch(Dispatchers.Default){
            repository.insertRecordCodeScan(codeScanEntity)
        }
    }


    fun delete(codeScanEntity: CodeScanEntity){
        viewModelScope.launch(Dispatchers.Default){
            repository.delete(codeScanEntity)

    }

}}

