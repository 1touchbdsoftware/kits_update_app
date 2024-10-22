package com.bdpolice.kms.ui.repository.profile

import android.util.Log
import com.bdpolice.kms.api.API
import com.bdpolice.kms.data.datamanager.DataManager.ERROR_OCCURRED
import com.bdpolice.kms.data.datamanager.DataManager.NO_INTERNET
import com.bdpolice.kms.data.datamanager.DataManager.SERVER_ERROR
import com.bdpolice.kms.ui.dto.ProfileResponse
import com.bdpolice.kms.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val TAG = "Profile"
class Profile @Inject constructor(
    private val api: API
) {

    private val mutableStateFlow = MutableStateFlow<NetworkResult<ProfileResponse>>(NetworkResult.Empty())
    val stateFlow : StateFlow<NetworkResult<ProfileResponse>> get() = mutableStateFlow

    suspend fun getProfile(token : String, employeeCode : String) {
        try {
            val response = api.getProfile(
                tokenData = token,
                employeeCode = employeeCode
            )
            mutableStateFlow.emit(NetworkResult.Loading())
            if(response.isSuccessful && response.body() != null){
                mutableStateFlow.emit(NetworkResult.Success(response.body()!!))
            }else {
                mutableStateFlow.emit(NetworkResult.Error(ERROR_OCCURRED))
            }
        }catch (e: HttpException) {
            mutableStateFlow.emit(NetworkResult.Error(SERVER_ERROR))
            Log.d(TAG, "signInAccount: $SERVER_ERROR")
        } catch (e: IOException) {
            mutableStateFlow.emit(NetworkResult.Error(NO_INTERNET))
            Log.d(TAG, "signInAccount: $NO_INTERNET")
        }
    }
}