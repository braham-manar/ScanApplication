package com.example.scanmodule.data.dataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "LoginUser")
data class LoginUserEntity(
    @PrimaryKey()

    @ColumnInfo(name = "mail") val mail :String?=null,

    @ColumnInfo(name = "password") val password: String?=null)



