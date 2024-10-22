package com.bdpolice.kms.data.model.kits

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KitsListResponseItem(
    val AllocationStatus: String,
    val DueQuantity_FiscalYear_VW: Int,
    val EligibleDate: String,
    val EligibleQuantity_FiscalYear_VW: Int,
    val InvoiceQuantity_FiscalYear_VW: Int,
    val LastDate_VW: String,
    val LogisticsSize_VW: String,
    val OrderBy_VW: Int,
    val ProductCode: String,
    val ProductEligiblity_VW: String,
    val ProductName_VW: String,
    val Quantity_Last_VW: Int,
    val Serial_VW: Int,
    var selected : Boolean = false
) : Parcelable