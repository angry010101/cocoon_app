package com.yakymovych.simon.yogaapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.ajalt.timberkt.Timber
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.ui.BaseActivity
import com.yakymovych.simon.yogaapp.ui.BaseViewModel
import kotlinx.android.synthetic.main.activity_main2.*
import javax.inject.Inject
import com.yakymovych.simon.yogaapp.data.Sort
import android.view.View

import androidx.appcompat.widget.PopupMenu
import dagger.android.support.DaggerAppCompatActivity


class MainActivity : DaggerAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
