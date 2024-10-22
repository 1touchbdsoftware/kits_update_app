package com.bdpolice.kms.ui.repository.datastore

import com.bdpolice.kms.ui.datastore.GetBoolData
import com.bdpolice.kms.ui.datastore.GetIntData
import com.bdpolice.kms.ui.datastore.GetStringData
import com.bdpolice.kms.ui.datastore.SaveBoolData
import com.bdpolice.kms.ui.datastore.SaveIntData
import com.bdpolice.kms.ui.datastore.SaveStringData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataStore @Inject constructor(
    private val stringData: GetStringData,
    private val intData: GetIntData,
    private val boolData: GetBoolData,
    private val addStringData: SaveStringData,
    private val addIntData: SaveIntData,
    private val addBoolData: SaveBoolData
) {

    fun getStringData(key : String) : Flow<String> = stringData(key = key)
    fun getIntData(key : String) : Flow<Int> = intData(key = key)
    fun getBoolData(key : String) : Flow<Boolean> = boolData(key = key)
    suspend fun saveStringData(key : String, value : String){
        addStringData(key = key, value = value)
    }
    suspend fun saveIntData(key : String, value : Int){
        addIntData(key = key, value = value)
    }
    suspend fun saveBoolData(key : String, value : Boolean){
        addBoolData(key = key, value = value)
    }
}