package com.bdpolice.kms.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TOKEN_API {


    @POST("token")
    suspend fun getToken(@Body data : String) : Response<String>
}