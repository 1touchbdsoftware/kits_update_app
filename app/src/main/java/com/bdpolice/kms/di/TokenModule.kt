package com.bdpolice.kms.di

import com.bdpolice.kms.api.TOKEN_API
import com.bdpolice.kms.data.datamanager.DataManager
import com.bdpolice.kms.di.named.Token
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TokenModule {

    @Provides
    @Singleton
    @Token
    fun provideToken() : Retrofit = Retrofit.Builder().baseUrl(DataManager.TOKEN)
        .addConverterFactory(ScalarsConverterFactory.create()).build()

    @Provides
    @Singleton
    fun getToken(@Token retrofit: Retrofit) : TOKEN_API = retrofit.create(TOKEN_API::class.java)
}