package com.bdpolice.kms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.bdpolice.kms.data.datamanager.DataManager
import com.bdpolice.kms.databinding.VideotutorialBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class VideoTutorial : Fragment() {

    private val binding by lazy { VideotutorialBinding.inflate(layoutInflater) }
    private lateinit var player : ExoPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        player = ExoPlayer.Builder(requireContext()).build()
        binding.PlayerView.setPlayer(player)
        val mediaitem: MediaItem = MediaItem.fromUri(DataManager.KITS_VIDEO)
        player.setMediaItem(mediaitem)
        player.prepare()
        player.playWhenReady = true
    }


    override fun onDestroyView() {
        super.onDestroyView()
        player.playWhenReady = false
        player.release()
    }
}