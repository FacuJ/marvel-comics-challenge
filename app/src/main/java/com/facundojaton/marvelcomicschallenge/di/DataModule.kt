package com.facundojaton.marvelcomicschallenge.di

import com.facundojaton.marvelcomicschallenge.remote.MarvelRemoteDataSource
import com.facundojaton.marvelcomicschallenge.remote.MarvelRemoteService
import com.facundojaton.marvelcomicschallenge.repositories.MarvelRepository
import com.facundojaton.marvelcomicschallenge.repositories.RemoteDataSource
import com.facundojaton.marvelcomicschallenge.utils.APIConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideMarvelRepository(remote: MarvelRemoteDataSource) = MarvelRepository(remote)
}
