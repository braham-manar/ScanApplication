package com.example.scanmodule.dataBase

import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDAO:AppDAO) {
    fun getRecords():List<UserEntity>{
       return  appDAO.getRecords()

    }
        fun insertRecord(userEntity: UserEntity){
            appDAO.inserRecord(userEntity)
        }
}