package com.bdpolice.kms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bdpolice.kms.data.datamanager.DataManager
import com.bdpolice.kms.data.model.signin.SignInData
import com.bdpolice.kms.databinding.SigninBinding
import com.bdpolice.kms.ui.viewmodel.DataStoreViewModel
import com.bdpolice.kms.ui.viewmodel.NetworkViewModel
import com.bdpolice.kms.utils.AlerterDialog.alert_error
import com.bdpolice.kms.utils.AlerterDialog.info
import com.bdpolice.kms.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

private const val TAG = "SignIn"
@AndroidEntryPoint
class SignIn : Fragment() {

    private val binding by lazy { SigninBinding.inflate(layoutInflater) }
    private val networkViewModel by viewModels<NetworkViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        signInResponse()
    }

    private fun signInResponse(){
        binding.apply {
            lifecycleScope.launch {
                networkViewModel.stateFlowSignIn.collect{
                    when (it){
                        is NetworkResult.Success -> {
                            SignInBtn.complete(true)

                            if(it.data != null){
                                if(it.data.UserCode != null){
                                    Intent(requireActivity(), MainActivity::class.java).apply {
                                        startActivity(this)
                                        requireActivity().finish()
                                    }
                                }else {
                                    info(
                                        resources.getString(R.string.error),
                                        resources.getString(R.string.wrong_credentials),
                                        supportFragmentManager = childFragmentManager
                                    )
                                }
                            }else {
                                info(
                                    resources.getString(R.string.error),
                                    resources.getString(R.string.wrong_credentials),
                                    supportFragmentManager = childFragmentManager
                                )
                            }

                        }
                        is NetworkResult.Loading -> {
                            Log.d(TAG, "loading: ")
                        }
                        is NetworkResult.Empty -> {

                        }
                        is NetworkResult.Error -> {
                            SignInBtn.complete(true)
                            alert_error(
                                resources.getString(R.string.error),
                                it.message.toString(),
                                supportFragmentManager = childFragmentManager
                            )
                        }
                    }
                }
            }
        }

    }

    private fun initView(){
        binding.apply {
            ResetPasswordBtn.setOnClickListener {
                SignInDirections.actionSignInToBPDialog().apply {
                    findNavController().navigate(this)
                }
            }

            SignInBtn.setOnClickListener {
                val bp = BPInput.editText?.text.toString().trim()
                val password = PasswordInput.editText?.text.toString().trim()

                if(bp.isEmpty()){
                    EditTextBP.error = resources.getString(R.string.enter_bp)
                }else if(password.isEmpty()){
                    EditTextPassword.error = resources.getString(R.string.enter_password)
                }else {
                    SignInBtn.start()
                   networkViewModel.signInAccount(
                        id = bp,
                        password = password
                    )
                }
            }
        }
    }

}