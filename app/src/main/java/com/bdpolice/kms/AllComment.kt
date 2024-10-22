package com.bdpolice.kms

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bdpolice.kms.databinding.AllcommentBinding
import com.bdpolice.kms.ui.viewmodel.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "AllComment"
@AndroidEntryPoint
class AllComment : Fragment() {

    private val binding by lazy {
        AllcommentBinding.inflate(layoutInflater)
    }
    private val args by navArgs<AllCommentArgs>()
    private val viewmodel by viewModels<NetworkViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}