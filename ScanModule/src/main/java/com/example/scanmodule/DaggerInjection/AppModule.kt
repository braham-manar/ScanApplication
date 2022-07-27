package com.example.scanmodule.DaggerInjection

import android.app.Application
import com.example.scanmodule.data.Api.Box2homeApi
import com.example.scanmodule.data.dataBase.AppDAO
import com.example.scanmodule.data.dataBase.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    private const val BASE_URL = "https://dev-api.box2home.xyz/api/"


    @Provides
    @Singleton
    fun provideBox2homeApi(): Box2homeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Box2homeApi::class.java)
    }

}