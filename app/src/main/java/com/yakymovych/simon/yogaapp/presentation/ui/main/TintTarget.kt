package com.yakymovych.simon.yogaapp.presentation.ui.main

import android.content.res.ColorStateList
import android.graphics.ColorMatrixColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.transition.Transition


class TintTarget(view: ImageView?,
                 private val placeholderColor: ColorMatrixColorFilter?)
    : ImageViewTarget<Drawable?>(view) {


    override fun setDrawable(drawable: Drawable?) {
        // don't tint, this is called with a cross-fade drawable,
        // and we need the inner drawables tinted, but not this
        if (placeholderColor != null){
            view.colorFilter = placeholderColor
        }
        else {
            view.clearColorFilter()
        }
        super.setDrawable(drawable)
    }

    override fun onLoadStarted(placeholder: Drawable?) {
        if (placeholderColor != null){
            view.colorFilter = placeholderColor
        }
        else {
            view.clearColorFilter()
        }
        super.onLoadStarted(tint(placeholder, placeholderColor))
    }

    override fun onLoadCleared(placeholder: Drawable?) {
        if (placeholderColor != null){
            view.colorFilter = placeholderColor
        }
        else {
            view.clearColorFilter()
        }
        super.onLoadCleared(tint(placeholder, placeholderColor))
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable?>?) {
        val tinted: Drawable = tint(resource, placeholderColor)!!
        if (placeholderColor != null){
            view.colorFilter = placeholderColor
        }
        else {
            view.clearColorFilter()
        }
        view.setImageDrawable(tinted)
    }

    override fun setResource(resource: Drawable?) {
        if (placeholderColor != null){
            view.colorFilter = placeholderColor
        }
        else {
            view.clearColorFilter()
        }
        view.setImageDrawable(tint(resource, placeholderColor))
    }

    private fun tint(input: Drawable?, tint: ColorMatrixColorFilter?): Drawable? {
        if (input == null) {
            return null
        }
        val wrappedDrawable = DrawableCompat.wrap(input)
        if (tint != null) {
            wrappedDrawable.colorFilter = tint
        }
        else {
            wrappedDrawable.clearColorFilter()
        }
        return wrappedDrawable
    }
}