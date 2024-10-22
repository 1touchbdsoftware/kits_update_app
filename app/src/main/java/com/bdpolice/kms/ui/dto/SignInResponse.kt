package com.bdpolice.kms.ui.dto

import androidx.annotation.Keep

@Keep
data class SignInResponse(
    val BranchName: Any,
    val EmployeeCode: String,
    val GroupName: String?,
    val SecurityNameId: Any,
    val SecurityText: Any,
    val UserCode: String?,
    val UserFullName: String,
    val UserName: String
)