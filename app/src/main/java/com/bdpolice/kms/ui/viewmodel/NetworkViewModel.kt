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
    private val question: Question
) : ViewModel() {

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
            token = "Bearer yDXvzS6E2rZBLDoWFYRBKkiV61BxDxp5-ji_8X8Omu7a1mWPSQMsCw29yXyHIGZcPo4DvqFbDXABmfhdM8B03-QuP_00b1Nx8t_Frj0_RQYd8rTAJCw_Tg58Y4GEm7WXZg_ieutuVccMwpFvT-x8xGcFHBPUt8s8QNUEDOTc2Glh0F3sC84u5HS3I6vLi34O3wGsOsQhRT63ZjHNABm2PKvroe_dmDNNAEWcdHeNqzwBw8IuzkxHEZLysTRxawSgxUJwzONsvivCEYnHz5EgxgXtoi1WXg33ge3YgDybZXrJio8O7yG3ktn0DoD44nfU"
        )

        getLogistics(
            token = "Bearer yDXvzS6E2rZBLDoWFYRBKkiV61BxDxp5-ji_8X8Omu7a1mWPSQMsCw29yXyHIGZcPo4DvqFbDXABmfhdM8B03-QuP_00b1Nx8t_Frj0_RQYd8rTAJCw_Tg58Y4GEm7WXZg_ieutuVccMwpFvT-x8xGcFHBPUt8s8QNUEDOTc2Glh0F3sC84u5HS3I6vLi34O3wGsOsQhRT63ZjHNABm2PKvroe_dmDNNAEWcdHeNqzwBw8IuzkxHEZLysTRxawSgxUJwzONsvivCEYnHz5EgxgXtoi1WXg33ge3YgDybZXrJio8O7yG3ktn0DoD44nfU",
            employeeCode = "0A6D1920-D61B-40D3-A690-DD3A3BC20688"
        )

        kitsList(
            token = "Bearer yDXvzS6E2rZBLDoWFYRBKkiV61BxDxp5-ji_8X8Omu7a1mWPSQMsCw29yXyHIGZcPo4DvqFbDXABmfhdM8B03-QuP_00b1Nx8t_Frj0_RQYd8rTAJCw_Tg58Y4GEm7WXZg_ieutuVccMwpFvT-x8xGcFHBPUt8s8QNUEDOTc2Glh0F3sC84u5HS3I6vLi34O3wGsOsQhRT63ZjHNABm2PKvroe_dmDNNAEWcdHeNqzwBw8IuzkxHEZLysTRxawSgxUJwzONsvivCEYnHz5EgxgXtoi1WXg33ge3YgDybZXrJio8O7yG3ktn0DoD44nfU",
            employeeCode = "0A6D1920-D61B-40D3-A690-DD3A3BC20688",
            year = "2023"
        )

        getProfile(
            token = "Bearer yDXvzS6E2rZBLDoWFYRBKkiV61BxDxp5-ji_8X8Omu7a1mWPSQMsCw29yXyHIGZcPo4DvqFbDXABmfhdM8B03-QuP_00b1Nx8t_Frj0_RQYd8rTAJCw_Tg58Y4GEm7WXZg_ieutuVccMwpFvT-x8xGcFHBPUt8s8QNUEDOTc2Glh0F3sC84u5HS3I6vLi34O3wGsOsQhRT63ZjHNABm2PKvroe_dmDNNAEWcdHeNqzwBw8IuzkxHEZLysTRxawSgxUJwzONsvivCEYnHz5EgxgXtoi1WXg33ge3YgDybZXrJio8O7yG3ktn0DoD44nfU",
            employeeCode = "0A6D1920-D61B-40D3-A690-DD3A3BC20688"
        )

    }
}