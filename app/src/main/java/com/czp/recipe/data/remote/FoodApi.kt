package com.czp.recipe.data.remote

import com.czp.recipe.data.model.FoodRecipeBaseInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    // baseUrl: https://api.spoonacular.com/recipes/complexSearch?apiKey=a00ff51ceb9f4b15959db96db2429eaf&addRecipeInformation=true&number=50&cuisine=Chinese&fillIngredients=true
    // 获取所有数据
    @GET("recipes/complexSearch?apiKey=a00ff51ceb9f4b15959db96db2429eaf&addRecipeInformation=true&number=50&cuisine=Chinese&fillIngredients=true")
    suspend fun fetchFoodRecipes(@Query(UrlString.TYPE) type: String): Response<FoodRecipeBaseInfo>


}