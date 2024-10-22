package com.bdpolice.kms.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bdpolice.kms.R
import com.bdpolice.kms.databinding.ChangepasswordBinding
import com.bdpolice.kms.ui.viewmodel.NetworkViewModel
import com.bdpolice.kms.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val TAG = "ChangePassword"
@AndroidEntryPoint
class ChangePassword : Fragment() {

    private val binding by lazy { ChangepasswordBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<NetworkViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getQuestion()
        initView()
    }


    private fun initView() {
        binding.apply {
            SCodeQuestion.setOnClickListener {
                ChangePasswordDirections.actionChangePasswordToQuestionBottomSheet().apply {
                    findNavController().navigate(this)
                }
            }
        }
    }
    private fun getQuestion() {
        binding.apply {
            lifecycleScope.launch {
                viewmodel.stateFlowQuestion.collect{
                    when(it){
                        is NetworkResult.Empty -> {

                        }
                        is NetworkResult.Error -> {
                            Log.d(TAG, "error: ${it.message}")
                        }
                        is NetworkResult.Loading -> {
                            Log.d(TAG, "loading: ")
                        }
                        is NetworkResult.Success -> {
                            Log.d(TAG, "success: ${it.data}")
                        }
                    }
                }
            }
            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("key")?.observe(viewLifecycleOwner) { result ->
                question = result
            }
        }
    }

}