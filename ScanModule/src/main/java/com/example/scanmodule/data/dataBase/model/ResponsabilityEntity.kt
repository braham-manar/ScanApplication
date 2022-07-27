package com.example.scanmodule.data.dataBase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Responsibilité")
data class ResponsabilityEntity(
    @PrimaryKey()
    @ColumnInfo("id") var id:Int,
    @ColumnInfo("code") var code:String,
    @ColumnInfo("label") var label:String){

    override fun toString(): String {
        //return super.toString()
        return label
    }
}



