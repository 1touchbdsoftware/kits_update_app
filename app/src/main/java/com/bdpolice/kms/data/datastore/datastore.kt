package com.bdpolice.kms.data.datastore

import kotlinx.coroutines.flow.Flow


interface datastore {

    suspend fun saveString(key : String, value : String)
    suspend fun saveInt(key : String, value : Int)
    suspend fun saveBoolean(key : String, value : Boolean)


    fun getString(key : String) : Flow<String>
    fun getInt(key : String) : Flow<Int>
    fun getBoolean(key : String) : Flow<Boolean>
}