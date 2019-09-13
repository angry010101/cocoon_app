package com.yakymovych.simon.yogaapp.presentation.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.presentation.ui.BaseFragment
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel
import com.yakymovych.simon.yogaapp.presentation.di.DaggerViewModelFactory
import javax.inject.Inject

class LoginFragment : BaseFragment() {
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    lateinit var loginViewModel: LoginViewModel

    override fun getBaseViewModel(): BaseViewModel = loginViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        loginViewModel = ViewModelProvider(this, viewModeFactory).get(LoginViewModel::class.java)
        val rootView: com.yakymovych.simon.yogaapp.databinding.LoginFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.login_fragment, container, false)
        rootView.viewModel = loginViewModel
        rootView.lifecycleOwner = this

        return rootView.root
    }

}