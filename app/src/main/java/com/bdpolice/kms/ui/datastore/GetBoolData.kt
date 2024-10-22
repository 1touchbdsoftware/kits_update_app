package com.bdpolice.kms.ui.datastore

import com.bdpolice.kms.data.datastore.datastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBoolData @Inject constructor(
    private val datastore: datastore
) {

    operator fun invoke(key : String) : Flow<Boolean> = datastore.getBoolean(key = key)
}