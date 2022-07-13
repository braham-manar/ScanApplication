package com.example.scanmodule.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDAO {
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords():List<UserEntity>
    @Insert
    fun inserRecord(userEntity:UserEntity)


  //  ----------- scan -------------
  @Query("SELECT * FROM codeScan ORDER BY code DESC")
  fun getRecordsCodeScan():List<CodeScanEntity>


   @Query("SELECT * FROM codeScan ORDER BY code DESC")
  fun getRecordsCodeScanAsLiveData():LiveData<List<CodeScanEntity>>


    @Insert
    fun inserRecordCodeScan(codeScanEntity: CodeScanEntity)
}