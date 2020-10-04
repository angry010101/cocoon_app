package com.yakymovych.simon.yogaapp.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.presentation.di.DaggerViewModelFactory
import com.yakymovych.simon.yogaapp.presentation.ui.BaseFragment
import com.yakymovych.simon.yogaapp.presentation.ui.BaseViewModel
import com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview.TasksAdapter
import kotlinx.android.synthetic.main.activity_main2.view.*
import javax.inject.Inject


class MainFragment : BaseFragment() {
    @Inject
    lateinit var viewModeFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel

    override fun getBaseViewModel(): BaseViewModel = mainViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        mainViewModel = ViewModelProvider(this, viewModeFactory).get(MainViewModel::class.java)
        val rootView = inflater.inflate(R.layout.activity_main2, container, false)
        val llm = LinearLayoutManager(requireContext())
        llm.orientation = LinearLayoutManager.VERTICAL
        rootView.tasks_recycler_view.layoutManager = llm;

        val adapter = TasksAdapter { user ->
            val bundle = Bundle()
            bundle.putString("userLogin", user.login)
            findNavController().navigate(R.id.action_mainFragment_to_DetailsFragment, bundle)
        }
        mainViewModel.tasks.observe(this, Observer {
            adapter.submitList(it)
        })
        rootView.tasks_recycler_view.adapter = adapter
        (rootView.tasks_recycler_view.adapter as TasksAdapter).notifyDataSetChanged()
        rootView.swipeContainer.setOnRefreshListener {
            mainViewModel.refreshTasks();
            rootView.swipeContainer.isRefreshing = false
        }
        return rootView
    }
}