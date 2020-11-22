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
import com.yakymovych.simon.yogaapp.data.api.responses.Result
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
        mainViewModel = ViewModelProvider(activity!!, viewModeFactory).get(MainViewModel::class.java)
        rootView = inflater.inflate(R.layout.content_main, container, false)
        val item = arguments?.getInt("newsPosition", -1) ?: -1
        val isSaved = arguments?.getBoolean("isSaved", false) ?: false
        if (item >= 0) {
            val data = if (isSaved){
                mainViewModel.dao.favorites().get(item)
            }
            else {
                mainViewModel.data.value?.get(item)
            }
            data?.let {
                setUser(it)
            }
        }
        return rootView
    }

    fun setUser(item: Result) {
        if (item.multimedia.isNotEmpty()){
            Glide.with(this).load(item.multimedia.get(0).url)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(rootView.imageView)
        }
        if (!item.image.isNullOrEmpty()){
            Glide.with(this).load(item.image)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(rootView.imageView)
        }
        rootView.followers.text = item.created_date.toString()
        rootView.following.text = item.updated_date.toString()
        rootView.name.text = item.title
        rootView.company.text = item.abstractText
        rootView.blog.text = item.short_url
    }
}