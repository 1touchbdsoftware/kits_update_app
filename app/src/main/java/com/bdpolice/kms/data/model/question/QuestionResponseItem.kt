package com.bdpolice.kms.data.model.question

import androidx.annotation.Keep

@Keep
data class QuestionResponseItem(
    val SecurityNameId: Int,
    val SecurityNameText: String
)