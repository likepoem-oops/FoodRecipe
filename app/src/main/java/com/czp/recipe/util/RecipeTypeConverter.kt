package com.czp.recipe.util

import androidx.room.TypeConverter
import com.czp.recipe.data.model.FoodRecipeBaseInfo
import com.czp.recipe.data.model.Result
import com.google.gson.Gson

class RecipeTypeConverter {
    // FoodRecipe -> String
    @TypeConverter
    fun foodRecipeToString(recipe: FoodRecipeBaseInfo): String {
        return Gson().toJson(recipe)
    }

    // String -> FoodRecipe
    @TypeConverter
    fun stringToFoodRecipe(string: String): FoodRecipeBaseInfo {
        return Gson().fromJson(string, FoodRecipeBaseInfo::class.java)
    }

    @TypeConverter
    fun resultToString(recipe: Result): String {
        return Gson().toJson(recipe)
    }

    @TypeConverter
    fun stringToResult(string: String): Result {
        return Gson().fromJson(string, Result::class.java)
    }
}