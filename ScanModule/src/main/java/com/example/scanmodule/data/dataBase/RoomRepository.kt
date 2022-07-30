package com.example.scanmodule.data.dataBase

import androidx.lifecycle.LiveData
import com.example.scanmodule.data.Api.model.Responsibility
import com.example.scanmodule.data.dataBase.model.CodeScanEntity
import com.example.scanmodule.data.dataBase.model.MotifEntity
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.example.scanmodule.data.dataBase.model.UserEntity
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
       fun getRecordsCodeScanAsLiveData():LiveData<List<CodeScanEntity>> {
           return appDAO.getRecordsCodeScanAsLiveData()

       }
    fun getRecordsRespnsabiliteAsLiveData():LiveData<List<ResponsabilityEntity>> {
           return appDAO.getRecordsRespnsabiliteAsLiveData()
    }
    fun getRecordsMotifAsLiveData():LiveData<List<MotifEntity>> {
           return appDAO.getRecordsMotifAsLiveData()
    }
        fun insertRecordCodeScan(codeScanEntity: CodeScanEntity){
            appDAO.inserRecordCodeScan(codeScanEntity)
        }

        fun delete(codeScanEntity: CodeScanEntity){
            appDAO.delete(codeScanEntity)

    }



}