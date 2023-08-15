package com.czp.recipe.data.fragment.recipe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView: ImageView, url: String) {
        // 将Url对应的图片下载下来 显示到imageView上
        // 使用Glide
        Glide.with(imageView).load(url).into(imageView)
    }
}