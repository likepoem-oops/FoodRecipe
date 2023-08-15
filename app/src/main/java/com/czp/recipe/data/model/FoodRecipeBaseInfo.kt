package com.czp.recipe.data.model


import com.google.gson.annotations.SerializedName

data class FoodRecipeBaseInfo(
    @SerializedName("results")
    val results: List<Result>
)