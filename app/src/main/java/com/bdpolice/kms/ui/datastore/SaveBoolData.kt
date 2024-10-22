package com.bdpolice.kms.ui.datastore

import com.bdpolice.kms.data.datastore.datastore
import javax.inject.Inject

class SaveBoolData @Inject constructor(
    private val datastore: datastore
) {

    suspend operator fun invoke(key : String, value : Boolean){
        datastore.saveBoolean(key = key, value = value)
    }
}