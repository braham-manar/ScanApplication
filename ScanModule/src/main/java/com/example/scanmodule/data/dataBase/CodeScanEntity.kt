package com.example.scanmodule.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "codeScan")
data class CodeScanEntity(
    @PrimaryKey()
    @ColumnInfo(name = "code")
    val code: String,



    @ColumnInfo(name = "scan_phase") val scan_phase: String?=null,
    @ColumnInfo(name = "scan_type") val scan_type: String?=null,
    @ColumnInfo(name = "scan_date") val scan_date: Long = 0
)


