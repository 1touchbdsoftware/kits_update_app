package com.bdpolice.kms.data.model.signin

import androidx.annotation.Keep

@Keep
data class SignInData(
    private val UserId : String,
    private val Password : String
)