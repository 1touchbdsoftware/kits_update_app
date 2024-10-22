package com.bdpolice.kms.ui.datastore

import com.bdpolice.kms.data.datastore.datastore
import javax.inject.Inject

class SaveStringData @Inject constructor(
    private val datastore: datastore
) {

    suspend operator fun invoke(key : String, value : String){
        datastore.saveString(key = key, value = value)
    }
    
}