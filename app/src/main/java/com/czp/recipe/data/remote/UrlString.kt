package com.czp.recipe.data.remote

sealed class UrlString {
    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val TYPE = "type"
        const val TYPE_MAIN_COURSE = "main course"
    }
}