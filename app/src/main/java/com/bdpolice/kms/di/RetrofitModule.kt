package com.bdpolice.kms.di

import com.bdpolice.kms.api.API
import com.bdpolice.kms.data.datamanager.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {


    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(DataManager.BASEURL).addConverterFactory(GsonConverterFactory.create()).build()


    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit) : API = retrofit.create(API::class.java)
}