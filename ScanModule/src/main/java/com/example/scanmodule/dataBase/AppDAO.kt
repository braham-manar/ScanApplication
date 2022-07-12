package com.example.scanmodule.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDAO {
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getRecords():List<UserEntity>
    @Insert
    fun inserRecord(userEntity:UserEntity)


}