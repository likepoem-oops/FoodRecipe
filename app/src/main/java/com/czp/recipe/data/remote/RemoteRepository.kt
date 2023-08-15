package com.czp.recipe.data.remote

import com.czp.recipe.data.model.FoodRecipeBaseInfo
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 */
class RemoteRepository {
    // 创建foodApi对象
    private val foodApi: FoodApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(UrlString.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(FoodApi::class.java)
    }

    // 外部访问接口，查询10条数据
    suspend fun fetchFoodRecipes(type: String): Response<FoodRecipeBaseInfo> {
        return foodApi.fetchFoodRecipes(type)
    }
}