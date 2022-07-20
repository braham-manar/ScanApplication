package com.example.scanmodule.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "codeScan")
data class CodeScanEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id:Int=0,

    @ColumnInfo(name = "code") val code: String?=null,


    @ColumnInfo(name = "scan_phase") val scan_phase: String?=null,
    @ColumnInfo(name = "scan_type") val scan_type: String?=null,
    @ColumnInfo(name = "scan_date") val scan_date: Long = 0
)


