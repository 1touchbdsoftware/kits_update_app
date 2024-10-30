package com.bdpolice.kms.ui.repository.token

import com.bdpolice.kms.api.TOKEN_API
import com.bdpolice.kms.data.datamanager.DataManager
import com.bdpolice.kms.data.datamanager.DataManager.NO_INTERNET
import com.bdpolice.kms.data.datamanager.DataManager.SERVER_ERROR
import com.bdpolice.kms.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class Token @Inject constructor(
    private val api: TOKEN_API
) {

    private val mutableStateFlow = MutableStateFlow<NetworkResult<String>>(NetworkResult.Empty())
    val stateFlow : StateFlow<NetworkResult<String>> get() = mutableStateFlow

    suspend fun getToken(data : String) {
        try {
            mutableStateFlow.emit(NetworkResult.Loading())
            val response = api.getToken(data)
            if(response.isSuccessful && response.body() != null){
                mutableStateFlow.emit(NetworkResult.Success(response.body()!!))
            }else {
                mutableStateFlow.emit(NetworkResult.Error(message = DataManager.ERROR_OCCURRED))
            }
        } catch (e: HttpException) {
            mutableStateFlow.emit(NetworkResult.Error(SERVER_ERROR))
        } catch (e: IOException) {
            mutableStateFlow.emit(NetworkResult.Error(NO_INTERNET))
        }
    }
}
