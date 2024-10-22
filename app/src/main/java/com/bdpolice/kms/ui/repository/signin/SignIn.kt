package com.bdpolice.kms.ui.repository.signin

import com.bdpolice.kms.api.API
import com.bdpolice.kms.data.datamanager.DataManager.EMPLOYEE_CODE
import com.bdpolice.kms.data.datamanager.DataManager.GROUP_NAME
import com.bdpolice.kms.data.datamanager.DataManager.IS_LOGIN_ACCOUNT
import com.bdpolice.kms.data.datamanager.DataManager.NO_INTERNET
import com.bdpolice.kms.data.datamanager.DataManager.SERVER_ERROR
import com.bdpolice.kms.data.datamanager.DataManager.USER_CODE
import com.bdpolice.kms.data.datamanager.DataManager.USER_FULL_NAME
import com.bdpolice.kms.data.datamanager.DataManager.USER_NAME
import com.bdpolice.kms.ui.datastore.SaveBoolData
import com.bdpolice.kms.ui.datastore.SaveStringData
import com.bdpolice.kms.ui.dto.SignInResponse
import com.bdpolice.kms.utils.NetworkResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignIn @Inject constructor(
    private val api: API,
    private val saveStringData: SaveStringData,
    private val saveBoolData: SaveBoolData
) {

    private val mutableStateFlow =
        MutableStateFlow<NetworkResult<SignInResponse>>(NetworkResult.Empty())
    val stateFlow: StateFlow<NetworkResult<SignInResponse>> get() = mutableStateFlow


    suspend fun sigInAccount(id: String, password: String, viewModelScope: CoroutineScope) {
        try {
            mutableStateFlow.emit(NetworkResult.Loading())
            val response = api.loginUser(userId = id, password = password)
            if (response.isSuccessful && response.body() != null) {
                saveData(response.body()!!, viewModelScope)
                mutableStateFlow.emit(NetworkResult.Success(response.body()!!))
            } else if (response.errorBody() != null) {
                mutableStateFlow.emit(NetworkResult.Error("Something went wrong"))
            } else {
                mutableStateFlow.emit(NetworkResult.Error("Something went wrong"))
            }
        } catch (e: HttpException) {
            mutableStateFlow.emit(NetworkResult.Error(SERVER_ERROR))
        } catch (e: IOException) {
            mutableStateFlow.emit(NetworkResult.Error(NO_INTERNET))
        }
    }

    private fun saveData(body: SignInResponse, viewModelScope: CoroutineScope) {
        viewModelScope.launch(Dispatchers.IO) { saveStringData(key = EMPLOYEE_CODE, value = body.EmployeeCode) }
        viewModelScope.launch(Dispatchers.IO) { saveStringData(key = USER_CODE, value = body.UserCode?: "") }
        viewModelScope.launch(Dispatchers.IO) { saveStringData(key = USER_NAME, value = body.UserName) }
        viewModelScope.launch(Dispatchers.IO) { saveStringData(key = USER_FULL_NAME, value = body.UserFullName) }
        viewModelScope.launch(Dispatchers.IO) { saveStringData(key = GROUP_NAME, value = body.GroupName?: "") }
        viewModelScope.launch(Dispatchers.IO) { saveBoolData(key = IS_LOGIN_ACCOUNT, value =  true) }
    }


}