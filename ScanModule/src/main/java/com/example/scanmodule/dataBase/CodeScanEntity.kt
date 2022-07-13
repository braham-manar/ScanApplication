package com.example.scanmodule.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "codeScan")
class CodeScanEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id :Int=0,

    @ColumnInfo(name = "code") val code: String?=null)


