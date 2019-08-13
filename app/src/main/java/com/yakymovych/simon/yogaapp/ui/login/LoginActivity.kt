package com.yakymovych.simon.yogaapp.ui.login

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.databinding.ActivityLoginBinding
import com.yakymovych.simon.yogaapp.ui.BaseActivity
import com.yakymovych.simon.yogaapp.ui.BaseViewModel
import com.yakymovych.simon.yogaapp.ui.main.MainActivity
import javax.inject.Inject

class LoginActivity : BaseActivity(){
    override fun getBaseViewModel(): BaseViewModel = loginViewModel

    @Inject
    lateinit var loginViewModel: LoginViewModel

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = loginViewModel
        binding.setLifecycleOwner(this)

        loginViewModel.goToMainActivity.observe(this, Observer {
            it?.let { if(it) startMain() }
        })


    }

    private fun startMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}