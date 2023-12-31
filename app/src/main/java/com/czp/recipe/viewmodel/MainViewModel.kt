package com.czp.recipe.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.czp.recipe.data.local.LocalRepository
import com.czp.recipe.data.local.entity.RecipeEntity
import com.czp.recipe.data.model.FoodRecipeBaseInfo
import com.czp.recipe.data.remote.RemoteRepository
import com.czp.recipe.util.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    // 网络请求对象
    private val remoteRepository = RemoteRepository()
    // 外部观察数据
    var recipes: MutableLiveData<NetworkResult<FoodRecipeBaseInfo>> = MutableLiveData()
    // 数据库操作对象
    private val localRepository: LocalRepository by lazy {
        LocalRepository(getApplication())
    }

    // 外部通过这个方法发起网络请求
    fun fetchFoodRecipes(type: String) {
        // 处于loading状态
        recipes.value = NetworkResult.Loading()
        // 首先查看数据库中是否有
        // 数据库读取
        viewModelScope.launch {
            val result = localRepository.getRecipes(type)
            result.collect {
                if (it.isNotEmpty()) {
                    recipes.value = NetworkResult.Success(it.first().recipe)
                }else {
                    // 判断是否有网络
                    if (checkConnection()) {
                        try {
                            val response = remoteRepository.fetchFoodRecipes(type)
                            if (response.isSuccessful) {
                                // 获取数据成功
                                recipes.value = NetworkResult.Success(response.body()!!)
                                // 需要将数据保存到数据库
                                localRepository.insertRecipe(RecipeEntity(0, type, response.body()!!))
                            }else {
                                // 获取数据失败
                                recipes.value = NetworkResult.Error(response.message())
                                Log.v("dao_fu", "com.czp.recipe.viewmodel.MainViewModel -> fetchFoodRecipes(type: String): Internet error...")
                            }
                        }catch (e: Exception) {
                            recipes.value = NetworkResult.Error("time out: ${e.message}")
                        }
                    } else {
                        recipes.value = NetworkResult.Error("No Internet...")
                    }
                }
            }
        }
    }

    // 判断是否有网络连接
    private fun checkConnection(): Boolean {
        // 获取网络连接管理器
        val connectivityManager =
            getApplication<Application>()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capability = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

        return when  {
            // wifi
            capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
            // 蜂窝数据
            capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
            // 以太
            capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            else -> false
        }
    }
}