package com.example.scanmodule.ui.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanmodule.data.dataBase.model.CodeScanEntity
import com.example.scanmodule.data.dataBase.RoomRepository
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

