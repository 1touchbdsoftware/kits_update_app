package com.bdpolice.kms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        initView()
    }

    private fun initView() {
        binding.apply {
            ListBtn.setOnClickListener {
                CommentDirections.actionCommentToAllComment2(kits = data.kits).apply {
                    findNavController().navigate(this)
                }
            }
        }
    }


}