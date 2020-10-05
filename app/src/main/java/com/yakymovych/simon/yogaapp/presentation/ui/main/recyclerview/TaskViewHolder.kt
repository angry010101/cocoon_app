package com.yakymovych.simon.yogaapp.presentation.ui.main.recyclerview

import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.yakymovych.simon.yogaapp.data.api.responses.GithubUser
import com.yakymovych.simon.yogaapp.presentation.ui.main.TintTarget
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.view_holder_task.view.*


class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView
    val dueBy: TextView
    val avatar: CircleImageView
    val wrapper: ViewGroup
    val taskPriority: View
    var negative: Boolean = false

    companion object {
        private val NEGATIVE = floatArrayOf(
                -1.0f, 0f, 0f, 0f, 255f, 0f, -1.0f, 0f, 0f, 255f, 0f, 0f, -1.0f, 0f, 255f, 0f, 0f, 0f, 1.0f, 0f)
    }

    var task: GithubUser? = null
        set(value) {
            Glide.with(this.itemView).clear(avatar);
            title.text = value?.login ?: ""
            dueBy.text = value?.type ?: ""
            Glide.with(this.itemView).load(value?.avatarUrl).diskCacheStrategy(DiskCacheStrategy.DATA).into(
                    TintTarget(avatar,
                            if (negative)
                                ColorMatrixColorFilter(NEGATIVE) else null));
            field = value
        }

    init {
        title = view.task_title
        dueBy = view.task_dueby
        avatar = view.imageView
        wrapper = view.wrapper
        taskPriority = view.task_priority
    }
}


class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}