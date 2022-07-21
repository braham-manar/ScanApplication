package com.example.scanmodule.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.scanmodule.data.dataBase.model.CodeScanEntity
import com.example.scanmodule.data.dataBase.model.UserEntity

@Database(entities = [UserEntity::class, CodeScanEntity::class],version=1,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun getDAO():AppDAO
    companion object{
        private var dbInstance :AppDataBase? =null
        fun getAppDB(context: Context):AppDataBase{
            if(dbInstance ==null){
                dbInstance = Room.databaseBuilder<AppDataBase>(
                    context.applicationContext,AppDataBase::class.java, "MYDB"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return dbInstance as AppDataBase
        }
    }
}