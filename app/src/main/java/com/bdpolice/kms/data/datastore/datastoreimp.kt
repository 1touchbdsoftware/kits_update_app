package com.bdpolice.kms.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bdpolice.kms.data.datamanager.DataManager.DataStorePref
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class datastoreimp @Inject constructor(val context: Context) : datastore {

    private val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = DataStorePref)


    override suspend fun saveString(key: String, value: String) {
        context.datastore.edit { data ->
            data[stringPreferencesKey(name = key)] = value
        }
    }

    override suspend fun saveInt(key: String, value: Int) {
        context.datastore.edit { data ->
            data[intPreferencesKey(name = key)] = value
        }
    }

    override suspend fun saveBoolean(key: String, value: Boolean) {
        context.datastore.edit { data ->
            data[booleanPreferencesKey(name = key)] = value
        }
    }

    override fun getString(key: String): Flow<String> {
        return context.datastore.data.map { data ->
            data[stringPreferencesKey(name = key)] ?: ""
        }
    }

    override fun getInt(key: String): Flow<Int> {
        return context.datastore.data.map { data ->
            data[intPreferencesKey(name = key)] ?: 0
        }
    }

    override fun getBoolean(key: String): Flow<Boolean> {
        return context.datastore.data.map { data ->
            data[booleanPreferencesKey(name = key)] ?: false
        }
    }
}