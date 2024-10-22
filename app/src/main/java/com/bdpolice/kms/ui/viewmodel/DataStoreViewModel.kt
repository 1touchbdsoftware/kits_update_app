package com.bdpolice.kms.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdpolice.kms.ui.repository.datastore.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStore: DataStore
) : ViewModel(){

    fun getStringData(key : String) : Flow<String> = dataStore.getStringData(key = key)
    fun getIntData(key : String) : Flow<Int> = dataStore.getIntData(key = key)
    fun getBoolData(key : String) : Flow<Boolean> = dataStore.getBoolData(key = key)


    fun saveStringData(key : String, value : String){
        try {
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveStringData(key = key, value = value)
        }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun saveIntData(key : String, value : Int){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStore.saveIntData(key = key, value = value)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun saveBoolData(key : String, value : Boolean){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                dataStore.saveBoolData(key = key, value = value)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}