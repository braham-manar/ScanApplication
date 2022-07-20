package com.example.scanmodule.dataBase

import androidx.lifecycle.LiveData
import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDAO:AppDAO) {
    fun getRecords():List<UserEntity>{
       return  appDAO.getRecords()

    }
        fun insertRecord(userEntity: UserEntity){
            appDAO.inserRecord(userEntity)
        }
    fun getRecordsCodeScan():List<CodeScanEntity>{
       return  appDAO.getRecordsCodeScan()

    }
       fun getRecordsCodeScanAsLiveData():LiveData<List<CodeScanEntity>>{
       return  appDAO.getRecordsCodeScanAsLiveData()

    }
        fun insertRecordCodeScan(codeScanEntity: CodeScanEntity){
            appDAO.inserRecordCodeScan(codeScanEntity)
        }
        fun delete(codeScanEntity: CodeScanEntity){
            appDAO.delete(codeScanEntity)

    }
}