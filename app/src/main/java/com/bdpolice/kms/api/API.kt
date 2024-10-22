package com.bdpolice.kms.api

import com.bdpolice.kms.ui.dto.KitsListResponse
import com.bdpolice.kms.ui.dto.LogisticsResponse
import com.bdpolice.kms.ui.dto.ProfileResponse
import com.bdpolice.kms.ui.dto.QuestionResponse
import com.bdpolice.kms.ui.dto.SignInResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface API {

    @GET("Password/QuestionList")
    suspend fun getQuestionList(@Header("Authorization")  tokenData : String) : Response<QuestionResponse>

    @GET("Logistics/Get")
    suspend fun getLogistics(
        @Header("Authorization")  tokenData : String, @Query("EmployeeCode") employeeCode : String
    ) : Response<LogisticsResponse>

    @GET("Profile/Get")
    suspend fun getProfile(
        @Header("Authorization")  tokenData : String, @Query("EmployeeCode") employeeCode : String
    ) : Response<ProfileResponse>

    @GET("KIT/Login")
    suspend fun loginUser(@Query("UserId") userId : String,
                          @Query("Password") password : String) : Response<SignInResponse>

    @GET("KIT/List")
    suspend fun getKitsList(@Header("Authorization") tokenData : String,
                            @Query("EmployeeCode") employeeCode : String,
                            @Query("FiscalYear") year : String) : Response<KitsListResponse>
}