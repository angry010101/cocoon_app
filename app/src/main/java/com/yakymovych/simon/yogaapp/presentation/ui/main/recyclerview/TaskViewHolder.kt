package com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview

import android.graphics.ColorMatrixColorFilter
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yakymovych.simon.yogaapp.data.api.responses.Result
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.view_holder_task.view.*


class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView
    val dueBy: TextView
    val avatar: CircleImageView
    val wrapper: ViewGroup
    val save: Button

    var item: Result? = null
        set(value) {
            Glide.with(this.itemView).clear(avatar);
            title.text = value?.title ?: ""
            dueBy.text = value?.created_date ?: ""
            if (!value?.image.isNullOrEmpty()){
                Glide.with(this.itemView).load(value?.image)
                        .diskCacheStrategy(DiskCacheStrategy.DATA).into(avatar);

            }
            else {
                if (value?.multimedia?.size!! > 0){
                    Glide.with(this.itemView).load(value.multimedia.get(0).url)
                            .diskCacheStrategy(DiskCacheStrategy.DATA).into(avatar);
                }
            }
            field = value
        }

    init {
        title = view.task_title
        dueBy = view.task_dueby
        avatar = view.imageView
        wrapper = view.wrapper
        save = view.favorites_save
    }
}