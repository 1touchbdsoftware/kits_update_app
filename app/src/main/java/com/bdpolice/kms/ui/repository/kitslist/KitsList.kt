package com.bdpolice.kms.ui.repository.kitslist

import com.bdpolice.kms.api.API
import com.bdpolice.kms.data.datamanager.DataManager.NO_INTERNET
import com.bdpolice.kms.data.datamanager.DataManager.SERVER_ERROR
import com.bdpolice.kms.ui.dto.KitsListResponse
import com.bdpolice.kms.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class KitsList @Inject constructor(private val api: API) {

    private val mutableStateFlow = MutableStateFlow<NetworkResult<KitsListResponse>>(NetworkResult.Empty())
    val stateFlow : StateFlow<NetworkResult<KitsListResponse>> = mutableStateFlow


    suspend fun kitsList(token : String, employeeCode : String, year : String){
        try {
            mutableStateFlow.emit(NetworkResult.Loading())
            val response = api.getKitsList(
                tokenData = token,
                employeeCode = employeeCode,
                year = year
            )
            if(response.isSuccessful && response.body() != null){
                mutableStateFlow.emit(NetworkResult.Success(response.body()!!))
            }else if (response.errorBody() != null) {
                mutableStateFlow.emit(NetworkResult.Error("Something went wrong"))
            } else {
                mutableStateFlow.emit(NetworkResult.Error("Something went wrong"))
            }
        }catch (e : HttpException){
            mutableStateFlow.emit(NetworkResult.Error(SERVER_ERROR))
        }catch (e : IOException){
            mutableStateFlow.emit(NetworkResult.Error(NO_INTERNET))
        }
    }
}