package com.czp.recipe.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.czp.recipe.data.model.FoodRecipeBaseInfo


@Entity(tableName = "foodRecipeTable")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val recipe: FoodRecipeBaseInfo
)