package com.czp.recipe.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.czp.recipe.data.local.entity.FavoriteEntity
import com.czp.recipe.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    // 插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    // 查询数据
    @Query("select * from foodRecipeTable where type=:type")
    fun getRecipes(type: String): Flow<List<RecipeEntity>>

    // 更新数据
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)

    /** -------------- Favorite -------------- **/

    // 查询所有收藏的食谱
    @Query("select * from favorite_table")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    // 添加收藏的食谱
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    // 删除收藏的食谱
    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)


}