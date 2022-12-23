package com.smilegateblog.smliegateblog.util
import androidx.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.smilegateblog.smliegateblog.R


fun ImageView.setImageUrl(url: String?, placeHolder: Drawable?, cornerValue: Int = 16) {
    val ph = placeHolder ?: ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground)

    Log.d("이미지", url.toString())

    Glide.with(context)
        .load(url)
        .placeholder(ph)
        .transform(CenterCrop(), RoundedCorners((cornerValue)))
        .into(this)
}