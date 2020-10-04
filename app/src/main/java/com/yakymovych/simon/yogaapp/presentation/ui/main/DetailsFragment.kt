package com.yakymovych.simon.yogaapp.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUserInfo
import com.yakymovych.simon.yogaapp.presentation.di.DaggerViewModelFactory
import com.yakymovych.simon.yogaapp.presentation.ui.BaseFragment
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel
import kotlinx.android.synthetic.main.content_main.view.*
import javax.inject.Inject


class DetailsFragment : BaseFragment() {
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun getBaseViewModel(): BaseViewModel = mainViewModel

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(this, viewModeFactory).get(MainViewModel::class.java)
        rootView = inflater.inflate(R.layout.content_main, container, false)
        mainViewModel.userData.observe(this, Observer {
            it?.let {
                setUser(it)
            }
        })
        val user = arguments?.getString("userLogin","") ?: ""
        if (user.isNotEmpty()){
            mainViewModel.requestUser(user)
        }
        return rootView
    }

    fun setUser(user: GithubUserInfo) {
        Glide.with(this).load(user.avatarUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(rootView.imageView)
        rootView.followers.text = user.followers.toString()
        rootView.following.text = user.following.toString()

        rootView.name.text = user.login
        rootView.company.text = user.company
        rootView.blog.text = user.blog
    }
}