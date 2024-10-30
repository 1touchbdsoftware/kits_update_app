package com.bdpolice.kms.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdpolice.kms.ui.datastore.GetStringData
import com.bdpolice.kms.ui.dto.KitsListResponse
import com.bdpolice.kms.ui.dto.LogisticsResponse
import com.bdpolice.kms.ui.dto.ProfileResponse
import com.bdpolice.kms.ui.dto.QuestionResponse
import com.bdpolice.kms.ui.dto.SignInResponse
import com.bdpolice.kms.ui.repository.kitslist.KitsList
import com.bdpolice.kms.ui.repository.logistics.Logistics
import com.bdpolice.kms.ui.repository.profile.Profile
import com.bdpolice.kms.ui.repository.questionlist.Question
import com.bdpolice.kms.ui.repository.signin.SignIn
import com.bdpolice.kms.ui.repository.token.Token
import com.bdpolice.kms.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val signIn: SignIn,
    private val kitsList: KitsList,
    private val getStringData: GetStringData,
    private val profile: Profile,
    private val logistics: Logistics,
    private val question: Question,
    private val token: Token
) : ViewModel() {

    //todo token
   val stateFlowToken: StateFlow<NetworkResult<String>> get() = token.stateFlow
    fun getToken(data : String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                token.getToken(data = data)
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    //todo token

    //todo question
    val stateFlowQuestion: StateFlow<NetworkResult<QuestionResponse>> get() = question.stateFlow
    fun getQuestion(token: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                question.getQuestion(
                    token = token
                )
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    //todo question

    //todo Logistics
    val stateFlowLogistics: StateFlow<NetworkResult<LogisticsResponse>> get() = logistics.stateFlow
    fun getLogistics(token: String, employeeCode: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                logistics.getLogistics(
                    token = token,
                    employeeCode = employeeCode
                )
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
    //todo Logistics

    //todo profile
    val profileStateFlow: StateFlow<NetworkResult<ProfileResponse>> get() = profile.stateFlow
    fun getProfile(token: String, employeeCode: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                profile.getProfile(
                    token = token,
                    employeeCode = employeeCode
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    //todo profile

    //todo signIn Account
    val stateFlowSignIn: StateFlow<NetworkResult<SignInResponse>> get() = signIn.stateFlow
    fun signInAccount(id: String, password: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                signIn.sigInAccount(id = id, password = password, viewModelScope)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    //todo signIn Account

    //todo kitsList
    val stateFlowKitsList: StateFlow<NetworkResult<KitsListResponse>> get() = kitsList.stateFlow
    fun kitsList(token: String, employeeCode: String, year: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                kitsList.kitsList(token = token, employeeCode = employeeCode, year = year)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    //todo kitsList


    init {
        getQuestion(
            token = "Bearer access_token Zr72IbOx2-YI2Ayr92TP_YVoBbTgWobfvbfxGrRRb2WF_qt6IbhcC9ZP7jsYn6D02J49fRoDt4sgvWsUENBu8dvZunDntJi81KrAlyrkWIY8RA4UvVFCfMCeNRD0295AXFKlCqmljIJbU3XDf71hwSdGwrjIO_Oy_0bzjtnSD0s5Y0KGGezkRRm9iQduJUDbgrmotdj3R-kxDWWV8CSmFw6MJ4fGU7zxg2ojED5accXSsI2aokie_psQQvGXOUpqWLgoCMFbvmS378UStHUoiWyN1rfhILCnYYnZKDybxbU"
        )

        getLogistics(
            token = "Bearer access_token Zr72IbOx2-YI2Ayr92TP_YVoBbTgWobfvbfxGrRRb2WF_qt6IbhcC9ZP7jsYn6D02J49fRoDt4sgvWsUENBu8dvZunDntJi81KrAlyrkWIY8RA4UvVFCfMCeNRD0295AXFKlCqmljIJbU3XDf71hwSdGwrjIO_Oy_0bzjtnSD0s5Y0KGGezkRRm9iQduJUDbgrmotdj3R-kxDWWV8CSmFw6MJ4fGU7zxg2ojED5accXSsI2aokie_psQQvGXOUpqWLgoCMFbvmS378UStHUoiWyN1rfhILCnYYnZKDybxbU",
            employeeCode = "53904ffd-1713-455c-98cf-bc30b20635a8"
        )

        kitsList(
            token = "Bearer access_token Zr72IbOx2-YI2Ayr92TP_YVoBbTgWobfvbfxGrRRb2WF_qt6IbhcC9ZP7jsYn6D02J49fRoDt4sgvWsUENBu8dvZunDntJi81KrAlyrkWIY8RA4UvVFCfMCeNRD0295AXFKlCqmljIJbU3XDf71hwSdGwrjIO_Oy_0bzjtnSD0s5Y0KGGezkRRm9iQduJUDbgrmotdj3R-kxDWWV8CSmFw6MJ4fGU7zxg2ojED5accXSsI2aokie_psQQvGXOUpqWLgoCMFbvmS378UStHUoiWyN1rfhILCnYYnZKDybxbU",
            employeeCode = "53904ffd-1713-455c-98cf-bc30b20635a8",
            year = "2023"
        )

        getProfile(
            token = "Bearer access_token Zr72IbOx2-YI2Ayr92TP_YVoBbTgWobfvbfxGrRRb2WF_qt6IbhcC9ZP7jsYn6D02J49fRoDt4sgvWsUENBu8dvZunDntJi81KrAlyrkWIY8RA4UvVFCfMCeNRD0295AXFKlCqmljIJbU3XDf71hwSdGwrjIO_Oy_0bzjtnSD0s5Y0KGGezkRRm9iQduJUDbgrmotdj3R-kxDWWV8CSmFw6MJ4fGU7zxg2ojED5accXSsI2aokie_psQQvGXOUpqWLgoCMFbvmS378UStHUoiWyN1rfhILCnYYnZKDybxbU",
            employeeCode = "53904ffd-1713-455c-98cf-bc30b20635a8"
        )

    }
}