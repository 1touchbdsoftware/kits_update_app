package com.bdpolice.kms.data.model.datastore

import com.bdpolice.kms.ui.datastore.GetBoolData
import com.bdpolice.kms.ui.datastore.GetIntData
import com.bdpolice.kms.ui.datastore.GetStringData
import com.bdpolice.kms.ui.datastore.SaveBoolData
import com.bdpolice.kms.ui.datastore.SaveIntData
import com.bdpolice.kms.ui.datastore.SaveStringData

data class datastore(
    private val saveStringData: SaveStringData,
    private val saveIntData: SaveIntData,
    private val saveBoolData: SaveBoolData,
    private val getStringData: GetStringData,
    private val getIntData: GetIntData,
    private val getBoolData: GetBoolData
)
