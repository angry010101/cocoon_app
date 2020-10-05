package com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import timber.log.Timber

class TasksAdapter(val onClick: (GithubUser) -> Unit) :
        PagedListAdapter<GithubUser, RecyclerView.ViewHolder>(GithubUser.DiffCallback) {
    companion object {
        private val TAG = this::class.java.simpleName
        private const val FOOTER_VIEW_TYPE = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == FOOTER_VIEW_TYPE){
            FooterViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.progressbar, parent, false))
        }
        else {
            TaskViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_task, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        if ((this.itemCount-1) == position){
            return FOOTER_VIEW_TYPE
        }
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TaskViewHolder){
            Timber.d(TAG, "Binding view holder at position $position")
            holder.negative = (position + 1) % 4 == 0
            holder.task = getItem(position)
            holder.wrapper.setOnClickListener {
                onClick(holder.task!!)
            }
        }
    }

}