package com.bdpolice.kms.ui.datastore

import com.bdpolice.kms.data.datastore.datastore
import javax.inject.Inject

class SaveIntData @Inject constructor(
    private val datastore: datastore
) {

    suspend operator fun invoke(key : String, value : Int){
        datastore.saveInt(key = key, value = value)
    }
}