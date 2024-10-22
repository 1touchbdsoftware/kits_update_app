package com.bdpolice.kms.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bdpolice.kms.R
import com.bdpolice.kms.databinding.AnnouncementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Announcement : Fragment() {

    private val binding by lazy { AnnouncementBinding.inflate(layoutInflater) }

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