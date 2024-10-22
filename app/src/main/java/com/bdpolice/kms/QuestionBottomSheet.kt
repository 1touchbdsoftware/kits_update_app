package com.bdpolice.kms

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bdpolice.kms.data.model.question.QuestionResponseItem
import com.bdpolice.kms.databinding.QuestionbottomsheetBinding
import com.bdpolice.kms.ui.adapter.SecurityQuestionAdapter
import com.bdpolice.kms.ui.viewmodel.NetworkViewModel
import com.bdpolice.kms.utils.NetworkResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "QuestionBottomSheet"
@AndroidEntryPoint
class QuestionBottomSheet : BottomSheetDialogFragment() {

    private val binding by lazy { QuestionbottomsheetBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<NetworkViewModel>()
    @Inject lateinit var securityQuestionAdapter: SecurityQuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllQuestion()
        initView()
    }

    private fun initView(){
        binding.apply {
            RecyclerView.apply {
                setHasFixedSize(true)
                adapter = securityQuestionAdapter
            }
            securityQuestionAdapter.setOnClick(object : SecurityQuestionAdapter.onClick {
                override fun click(item: QuestionResponseItem) {
                    Log.d(TAG, "click: $item")
                    findNavController().apply {
                        previousBackStackEntry?.savedStateHandle?.set("key", item.SecurityNameText)
                        popBackStack()
                    }
                }
            })
        }
    }


    private fun getAllQuestion(){
        binding.apply {
            lifecycleScope.launch {
                viewmodel.stateFlowQuestion.collect{
                    when(it){
                        is NetworkResult.Empty -> {

                        }
                        is NetworkResult.Error -> {
                            Log.d(TAG, "error: ${it.message}")
                        }
                        is NetworkResult.Success -> {
                            Log.d(TAG, "success: ${it.data}")
                            securityQuestionAdapter.submitList(it.data)
                        }
                        is NetworkResult.Loading -> {
                            Log.d(TAG, "loading: ")
                        }
                    }
                }
            }
        }
    }

}