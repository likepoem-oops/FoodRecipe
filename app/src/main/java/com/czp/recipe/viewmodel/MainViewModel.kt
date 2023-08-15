package com.czp.recipe.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.czp.recipe.data.model.FoodRecipeBaseInfo
import com.czp.recipe.data.remote.RemoteRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    // 网络请求对象
    private val remoteRepository = RemoteRepository()
    // 外部观察数据
    var recipes: MutableLiveData<FoodRecipeBaseInfo> = MutableLiveData()

    // 外部通过这个方法发起网络请求
    fun fetchFoodRecipes(type: String) {
        viewModelScope.launch{
            val response = remoteRepository.fetchFoodRecipes(type)
            if (response.isSuccessful) {
                recipes.value = response.body()
            }else {
                Log.v("dao_fu", "com.czp.recipe.viewmodel.MainViewModel ->fetchFoodRecipes(type: String): Internet error...")
            }
        }

    }
}