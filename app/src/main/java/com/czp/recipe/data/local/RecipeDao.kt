package com.czp.recipe.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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


}