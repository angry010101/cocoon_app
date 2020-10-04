package com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview

import android.graphics.ColorFilter
import android.graphics.ColorMatrixColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import timber.log.Timber

class TasksAdapter(val onClick: (GithubUser) -> Unit) :
        PagedListAdapter<GithubUser, TaskViewHolder>(GithubUser.DiffCallback) {
    companion object {
        private val TAG = this::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
            TaskViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_holder_task, parent, false))


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        Timber.d(TAG, "Binding view holder at position $position")
        holder.task = getItem(position)
        holder.wrapper.setOnClickListener {
            onClick(holder.task!!)
        }
        holder.negative = (position + 1) % 4 == 0
    }

}