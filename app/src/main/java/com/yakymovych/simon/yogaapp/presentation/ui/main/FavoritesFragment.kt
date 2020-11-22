package com.yakymovych.simon.yogaapp.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.presentation.di.DaggerViewModelFactory
import com.yakymovych.simon.yogaapp.presentation.ui.BaseFragment
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel
import com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview.TasksAdapter
import kotlinx.android.synthetic.main.activity_main2.view.*
import javax.inject.Inject


class FavoritesFragment : BaseFragment() {
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun getBaseViewModel(): BaseViewModel = mainViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(activity!!, viewModeFactory).get(MainViewModel::class.java)
        val rootView = inflater.inflate(R.layout.activity_main2, container, false)
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        rootView.tasks_recycler_view.layoutManager = llm;
        rootView.tasks_recycler_view.layoutManager = GridLayoutManager(requireContext(), 3)
        mainViewModel.dataSaved.observe(this, Observer {
            if (it == null) return@Observer
            val adapter = TasksAdapter(false, mainViewModel.dao, it) { _, pos ->
                val bundle = Bundle()
                bundle.putInt("newsPosition", pos)
                bundle.putBoolean("isSaved",true)
                findNavController().navigate(R.id.action_mainFragment_to_DetailsFragment, bundle)
            }
            rootView.tasks_recycler_view.adapter = adapter
            (rootView.tasks_recycler_view.adapter as TasksAdapter).notifyDataSetChanged()
        })
        mainViewModel.requestCached()
        return rootView
    }
}