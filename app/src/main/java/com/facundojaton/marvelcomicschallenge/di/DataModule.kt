package com.facundojaton.marvelcomicschallenge.di

import android.content.Context
import androidx.room.Room
import com.facundojaton.marvelcomicschallenge.db.MarvelDao
import com.facundojaton.marvelcomicschallenge.db.MarvelDatabase
import com.facundojaton.marvelcomicschallenge.db.RoomDataSource
import com.facundojaton.marvelcomicschallenge.remote.MarvelRemoteDataSource
import com.facundojaton.marvelcomicschallenge.remote.MarvelRemoteService
import com.facundojaton.marvelcomicschallenge.repositories.LocalDataSource
import com.facundojaton.marvelcomicschallenge.repositories.MarvelRepository
import com.facundojaton.marvelcomicschallenge.repositories.RemoteDataSource
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(APIConstants.BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMarvelRemoteService(retrofit: Retrofit): MarvelRemoteService =
        retrofit.create(MarvelRemoteService::class.java)

    @Provides
    @Singleton
    fun provideMarvelRemoteDataSource(service: MarvelRemoteService): RemoteDataSource =
        MarvelRemoteDataSource(service)

    @Provides
    @Singleton
    fun provideMarvelRepository(remote: MarvelRemoteDataSource, local: RoomDataSource) =
        MarvelRepository(remote,local)

    @Provides
    fun provideMarvelDao(database: MarvelDatabase): MarvelDao = database.marvelDao()

    @Provides
    @Singleton
    fun provideMarvelLocalDataSource(dao : MarvelDao) : LocalDataSource = RoomDataSource(dao)

    @Provides
    @Singleton
    fun provideMarvelDatabase(@ApplicationContext appContext: Context): MarvelDatabase =
        Room.databaseBuilder(
            appContext,
            MarvelDatabase::class.java,
            "marvel-db"
        ).build()
}
