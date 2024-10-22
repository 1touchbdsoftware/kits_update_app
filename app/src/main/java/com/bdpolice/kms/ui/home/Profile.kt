package com.bdpolice.kms.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bdpolice.kms.R
import com.bdpolice.kms.databinding.ProfileBinding
import com.bdpolice.kms.ui.dto.ProfileResponse
import com.bdpolice.kms.ui.viewmodel.NetworkViewModel
import com.bdpolice.kms.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "Profile"
@AndroidEntryPoint
class Profile : Fragment() {

    private val binding by lazy { ProfileBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<NetworkViewModel>()
    private var IsProfileDetailsShow = false
    private var IsLogisticsInfoShowe = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getLogisticsInfo()
    }
    
    private fun getLogisticsInfo(){
        binding.apply { 
            lifecycleScope.launch { 
                viewmodel.stateFlowLogistics.collect{
                    when(it){
                        is NetworkResult.Error -> {
                            SwipeRefreshLayout.isRefreshing  =false
                            Log.d(TAG, "getLogisticsInfo error: ${it.message}")
                        }
                        is NetworkResult.Loading -> {
                            Log.d(TAG, "loading: ")
                        }
                        is NetworkResult.Success -> {
                            SwipeRefreshLayout.isRefreshing  =false
                           logistics = it.data
                        }
                        is NetworkResult.Empty -> {

                        }
                    }
                }
            }
        }
    }


    private fun female() {
        binding.apply {
            UnderwearFemaleText.visibility = View.VISIBLE
            UnderwearFemale.visibility = View.VISIBLE

            CottonVestFemale.visibility = View.VISIBLE
            CottonVestFemaleText.visibility = View.VISIBLE
        }

    }

    private fun male() {
        binding.apply {
            UnderwearMaleText.visibility = View.VISIBLE
            UnderwearMale.visibility = View.VISIBLE

            CottonVestMale.visibility = View.VISIBLE
            CottonVestMaleText.visibility = View.VISIBLE
        }

    }

    private fun setProfileImage(data: ProfileResponse?) {
        binding.apply {
            val imagedata = data?.Photo?.replace("data:image/jpeg:base64", "")
            val code: ByteArray = Base64.decode(imagedata, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(code, 0, code.size)
            ProfileImage.setImageBitmap(bitmap)
        }

    }

    private fun initView(){
        binding.apply {
            lifecycleScope.launch {
                viewmodel.profileStateFlow.collect{
                    when(it){
                        is NetworkResult.Error -> {
                            SwipeRefreshLayout.isRefreshing  =false
                            Log.d(TAG, "error: ${it.message}")
                        }
                        is NetworkResult.Loading -> {
                            Log.d(TAG, "loading: ")

                        }
                        is NetworkResult.Success -> {
                            SwipeRefreshLayout.isRefreshing  =false
                           profile = it.data
                            setProfileImage(it.data)
                            if (it.data?.GenderName != null) {
                                if (it.data.GenderName == resources.getString(R.string.MaleBangla)
                                ) {
                                    male()
                                }
                                if (it.data.GenderName == resources.getString(R.string.FemaleBangla)
                                ) {
                                    female()
                                }
                            }
                        }
                        is NetworkResult.Empty -> {

                        }
                    }
                }
            }

            MoreInfoIconBtn.setOnClickListener {
                if (!IsProfileDetailsShow) {
                    IsProfileDetailsShow = true
                    binding.ProfileDetailsDiv.visibility = View.VISIBLE
                    binding.MoreInfoIconBtn.setImageResource(R.drawable.upicon)
                } else {
                    IsProfileDetailsShow = false
                    binding.ProfileDetailsDiv.visibility = View.GONE
                    binding.MoreInfoIconBtn.setImageResource(R.drawable.downicon)
                }
            }

            binding.LogisticsInfoBtn.setOnClickListener { view1 ->
                if (!IsLogisticsInfoShowe) {
                    IsLogisticsInfoShowe = true
                    binding.LogisticsInfoBtn.setImageResource(R.drawable.upicon)
                    binding.LogInfoDiv.visibility = View.VISIBLE
                } else {
                    IsLogisticsInfoShowe = false
                    binding.LogInfoDiv.visibility = View.GONE
                    binding.LogisticsInfoBtn.setImageResource(R.drawable.downicon)
                }
            }

            SwipeRefreshLayout.setOnRefreshListener {
                viewmodel.apply {
                    getLogistics(
                        token = "Bearer DrlV3JG3ZrxKm-MvE0FjRkFsrUu7zCITQ1IPGDM5OEBaPMcrVCv2xAou7GivBeUsd9JVSj5HyYKkqYYkZFmTIrw7yJIS7fc_bRjIYgrK0V7wgLSYzcyd3DgIZ8-aWQf6vNkfYoZ4wt7lBuJFtuBH2iUatO17vaN6i7OUn4kps5NYviPSnJqk3fB3idYFtLmifbNwCtrgOT1PZhdnRQMGai3Rp9gdnypYgrJzID_T4vDEKe5j11PrNwCejBfnH2lGW9dX4ZfAOIDdZhJuEPvq08OkCqp1QYlQ7qTRB4OvkoNeaCYwBrt9Wqb5h1Ktvfiu",
                        employeeCode = "0A6D1920-D61B-40D3-A690-DD3A3BC20688"
                    )
                    getProfile(
                        token = "Bearer DrlV3JG3ZrxKm-MvE0FjRkFsrUu7zCITQ1IPGDM5OEBaPMcrVCv2xAou7GivBeUsd9JVSj5HyYKkqYYkZFmTIrw7yJIS7fc_bRjIYgrK0V7wgLSYzcyd3DgIZ8-aWQf6vNkfYoZ4wt7lBuJFtuBH2iUatO17vaN6i7OUn4kps5NYviPSnJqk3fB3idYFtLmifbNwCtrgOT1PZhdnRQMGai3Rp9gdnypYgrJzID_T4vDEKe5j11PrNwCejBfnH2lGW9dX4ZfAOIDdZhJuEPvq08OkCqp1QYlQ7qTRB4OvkoNeaCYwBrt9Wqb5h1Ktvfiu",
                        employeeCode = "0A6D1920-D61B-40D3-A690-DD3A3BC20688"
                    )
                }
            }

            ChangePasswordBtn.setOnClickListener {
                ProfileDirections.actionProfileToChangePassword().apply {
                    findNavController().navigate(this)
                }
            }
        }
    }


}