package com.bdpolice.kms.di

import android.content.Context
import com.bdpolice.kms.data.datastore.datastore
import com.bdpolice.kms.data.datastore.datastoreimp
import com.bdpolice.kms.di.named.DataStore
import com.bdpolice.kms.ui.datastore.GetBoolData
import com.bdpolice.kms.ui.datastore.GetIntData
import com.bdpolice.kms.ui.datastore.GetStringData
import com.bdpolice.kms.ui.datastore.SaveBoolData
import com.bdpolice.kms.ui.datastore.SaveIntData
import com.bdpolice.kms.ui.datastore.SaveStringData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


    @Provides
    @Singleton
    @DataStore
    fun provideDataStore(@ApplicationContext context: Context) : datastore {
        return datastoreimp(context)
    }

    @Provides
    @Singleton
    fun provideDataStoreModel(@DataStore datastore: datastore) : com.bdpolice.kms.data.model.datastore.datastore =
        com.bdpolice.kms.data.model.datastore.datastore(
            saveStringData = SaveStringData(datastore = datastore),
            saveIntData = SaveIntData(datastore = datastore),
            saveBoolData = SaveBoolData(datastore = datastore),
            getStringData = GetStringData(datastore = datastore),
            getIntData = GetIntData(datastore = datastore),
            getBoolData = GetBoolData(datastore = datastore)
        )
}