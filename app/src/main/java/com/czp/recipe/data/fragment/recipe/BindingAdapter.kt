package com.czp.recipe.data.fragment.recipe

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.czp.recipe.R
import com.czp.recipe.data.model.Result
import com.google.android.material.chip.Chip

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView: ImageView, url: String) {
        // 将Url对应的图片下载下来 显示到imageView上
        // 使用Glide
        Glide.with(imageView).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("loadImageWithName")
    fun loadImageWithName(imageView: ImageView, url: String) {
        // 将Url对应的图片下载下来 显示到imageView上
        // 使用Glide
        val imageBaseUrl = "https://spoonacular.com/recipeImages/"
        Glide.with(imageView)
            .load(imageBaseUrl + url)
            .placeholder(R.drawable.fastfood)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("navigateToDetail")
    fun navigateToDetail(view: View, result: Result) {
        val header = view.findViewById<ImageView>(R.id.show_recipe_img)
        header.findNavController().navigate(R.id.action_recipeFragment_to_recipeDetailFragment)
    }
    @JvmStatic
    @BindingAdapter("changeStatus")
    fun changeStatus(chip: TextView, status: Boolean) {
        chip.isSelected = status
    }
}