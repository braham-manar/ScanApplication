package com.example.scanmodule.DaggerInjection

import android.app.Application
import com.example.scanmodule.data.dataBase.AppDAO
import com.example.scanmodule.data.dataBase.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun getAppDB(context:Application):AppDataBase{
        return AppDataBase.getAppDB(context)

    }@Singleton
    @Provides
    fun getAppDAO(appDB: AppDataBase): AppDAO {
        return appDB.getDAO()

    }


}