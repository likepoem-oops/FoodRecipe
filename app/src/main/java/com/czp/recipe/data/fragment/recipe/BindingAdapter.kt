package com.czp.recipe.data.fragment.recipe

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.czp.recipe.R
import com.czp.recipe.data.model.Result

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView: ImageView, url: String) {
        // 将Url对应的图片下载下来 显示到imageView上
        // 使用Glide
        Glide.with(imageView).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("navigateToDetail")
    fun navigateToDetail(view: View, result: Result) {
        val header = view.findViewById<ImageView>(R.id.show_recipe_img)
        header.findNavController().navigate(R.id.action_recipeFragment_to_recipeDetailFragment)
    }
}