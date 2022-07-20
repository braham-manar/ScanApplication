package com.example.scanmodule.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppDAO {
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserRecord(userEntity:UserEntity)


  //  ----------- scan -------------
  @Query("SELECT * FROM codeScan ORDER BY code DESC")
  fun getRecordsCodeScan():List<CodeScanEntity>


   @Query("SELECT * FROM codeScan ORDER BY scan_date DESC ")
  fun getRecordsCodeScanAsLiveData():LiveData<List<CodeScanEntity>>


    @Insert
    fun inserRecordCodeScan(codeScanEntity: CodeScanEntity)

    @Delete
    fun delete(codeScanEntity: CodeScanEntity)

    @Query("DELETE FROM codeScan WHERE id = :scanId")
    fun delete(scanId: Int)
}