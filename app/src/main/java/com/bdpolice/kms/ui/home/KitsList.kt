package com.bdpolice.kms.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bdpolice.kms.data.model.kits.KitsListResponseItem
import com.bdpolice.kms.databinding.KitslistBinding
import com.bdpolice.kms.ui.adapter.KitsListAdapter
import com.bdpolice.kms.ui.viewmodel.NetworkViewModel
import com.bdpolice.kms.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "KitsList"

@AndroidEntryPoint
class KitsList : Fragment() {

    private val binding by lazy { KitslistBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<NetworkViewModel>()
    @Inject
    lateinit var kitsListAdapter: KitsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getKitsList()
    }

    private fun initView() {
        binding.apply {
            kitsListAdapter.apply {
                onClickViewState(object : KitsListAdapter.onClickViewMore {
                    override fun onClickViewMore(item: KitsListResponseItem, position: Int) {
                        if (item.selected) {
                            item.selected = false
                        } else {
                            item.selected = true
                        }
                        kitsListAdapter.notifyItemChanged(position)
                    }
                })

                onClickCommentState(object : KitsListAdapter.onClickComment {
                    override fun comment(item: KitsListResponseItem, position: Int) {
                        KitsListDirections.actionKitsListToComment(
                            kits = item
                        ).apply {
                            findNavController().navigate(this)
                        }
                    }
                })
            }
            RecyclerView.apply {
                setHasFixedSize(true)
                adapter = kitsListAdapter
            }
            SwipeRefreshLayout.apply {
                setOnRefreshListener {
                    viewmodel.kitsList(
                        token = "Bearer c6WU0q0NmHcdaRYlO17LkiDqpzE8srILINIUSW7bPMr8IXOGiEpsBKpFTZfSWDpi_sULiVBQzHa91xPFEuJ5ARp2ZUYSeshiSXt_zXfmlQ9lhPN4AcW0F04C3G4YWj9_89JiRG2IoyV9jC5rWqtBr8jVy0Ws4JtgAlOgDGtATB1dGKenMmGEyqewKeDmMX-AhcziR2tdDqE4uNfG4Pmdos5r88o3hI8iD7h0IPRkD5f2KSU8O3Reg7oq3LDqy1pOWQcE9UZBCS2UQimHBVBiGUninm6sxoXgp0WxA8oJqLO0FQWzk2ISUWAAnKvrvt_G",
                        employeeCode = "0A6D1920-D61B-40D3-A690-DD3A3BC20688",
                        year = "2023"
                    )
                }
            }
        }
    }

    private fun getKitsList() {
        binding.apply {
            lifecycleScope.launch {
                viewmodel.stateFlowKitsList.collect {
                    when (it) {
                        is NetworkResult.Error -> {
                            SwipeRefreshLayout.isRefreshing = false
                            Log.d(TAG, "error: ${it.message}")
                        }

                        is NetworkResult.Loading -> {
                            Log.d(TAG, "Loading")
                        }

                        is NetworkResult.Success -> {
                            SwipeRefreshLayout.isRefreshing = false
                            kitsListAdapter.submitList(it.data)
                        }

                        is NetworkResult.Empty -> {

                        }

                    }
                }
            }
        }
    }
}