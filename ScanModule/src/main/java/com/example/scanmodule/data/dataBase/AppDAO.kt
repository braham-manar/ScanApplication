package com.example.scanmodule.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.scanmodule.data.Api.model.Responsibility
import com.example.scanmodule.data.dataBase.model.CodeScanEntity
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.example.scanmodule.data.dataBase.model.UserEntity

@Dao
interface AppDAO {
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords():List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserRecord(userEntity: UserEntity)


  //  ----------- scan -------------
  @Query("SELECT * FROM codeScan ORDER BY code DESC")
  fun getRecordsCodeScan():List<CodeScanEntity>


   @Query("SELECT * FROM codeScan ORDER BY scan_date DESC ")
  fun getRecordsCodeScanAsLiveData():LiveData<List<CodeScanEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserRecordCodeScan(codeScanEntity: CodeScanEntity)

    @Delete
    fun delete(codeScanEntity: CodeScanEntity)

    @Query("DELETE FROM codeScan WHERE code = :code")
    fun delete(code: String)

    //  ----------- scan -------------
    @Query("SELECT * FROM Responsibilité ")
    fun getRecordsRespnsabiliteAsLiveData():LiveData<List<ResponsabilityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserResponsabilite(responsibility: List<ResponsabilityEntity>? )

}