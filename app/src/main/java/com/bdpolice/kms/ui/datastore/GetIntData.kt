package com.bdpolice.kms.ui.datastore

import com.bdpolice.kms.data.datastore.datastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIntData @Inject constructor(
    private val datastore: datastore
) {

    operator fun invoke(key : String) : Flow<Int> = datastore.getInt(key = key)

}