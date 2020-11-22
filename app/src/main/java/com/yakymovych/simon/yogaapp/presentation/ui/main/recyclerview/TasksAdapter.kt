package com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yakymovych.simon.yogaapp.R
import com.yakymovych.simon.yogaapp.data.repository.FavoritesDao

class TasksAdapter(var visibleButton: Boolean, var dao: FavoritesDao, val data: List<com.yakymovych.simon.yogaapp.data.api.responses.Result>,
                   val onClick: (com.yakymovych.simon.yogaapp.data.api.responses.Result, Int) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = if (visibleButton){
            R.layout.view_holder_task;
        }
        else {
            R.layout.view_holder_grid;
        }
        return TaskViewHolder(LayoutInflater.from(parent.context)
                .inflate(layout, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TaskViewHolder) {
            val item = data[position]
            holder.item = item
            if (!visibleButton)  {
                holder.save.visibility = View.GONE
            }
            holder.wrapper.setOnClickListener {
                onClick(holder.item!!,position)
            }
            holder.save.setOnClickListener {
                Thread {
                    val i = data[position]
                    if (i.multimedia.size > 0){
                        i.image = i.multimedia[0].url
                    }
                    dao.insertNote(data[position])
                }.start()
            }
        }
    }

}