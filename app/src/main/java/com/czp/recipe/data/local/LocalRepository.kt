package com.czp.recipe.data.local

import android.content.Context
import com.czp.recipe.data.local.entity.FavoriteEntity
import com.czp.recipe.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

class LocalRepository(context: Context) {
    //
    private val recipeDao = RecipeDatabase.getInstance(context).getRecipeDao()

    // 插入数据
    suspend fun insertRecipe(recipeEntity: RecipeEntity) {
        recipeDao.insertRecipe(recipeEntity)
    }

    // 查询数据
    fun getRecipes(type: String): Flow<List<RecipeEntity>> {
        return recipeDao.getRecipes(type)
    }

    // 更新数据
    suspend fun updateRecipe(recipeEntity: RecipeEntity) {
        recipeDao.updateRecipe(recipeEntity)
    }

    /** -------------- Favorite -------------- **/
    // 查询所有收藏的食谱
    fun getAllFavorites(): Flow<List<FavoriteEntity>>{
        return recipeDao.getAllFavorites()
    }

    // 添加收藏的食谱
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity) {
        recipeDao.insertFavorite(favoriteEntity)
    }

    // 删除收藏的食谱
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        recipeDao.deleteFavorite(favoriteEntity)
    }
}