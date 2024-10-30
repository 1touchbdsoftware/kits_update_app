package com.bdpolice.kms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bdpolice.kms.databinding.CommentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class Comment : BottomSheetDialogFragment() {

    private val binding by lazy { CommentBinding.inflate(layoutInflater) }
    private val data by navArgs<CommentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    private fun initView(view: View) {
        binding.apply {
            ListBtn.setOnClickListener {
                CommentDirections.actionCommentToAllComment2(kits = data.kits).apply {
                    findNavController().navigate(this)
                }
            }

            RadioGroup.setOnCheckedChangeListener(android.widget.RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val rb: RadioButton = view.findViewById(checkedId)
               when(rb.text){
                   resources.getString(R.string.not_satisfied) -> {
                       CommentInput.isVisible = true
                   }else ->{
                   CommentInput.isVisible = false
                   }
               }
            })
        }
    }


}