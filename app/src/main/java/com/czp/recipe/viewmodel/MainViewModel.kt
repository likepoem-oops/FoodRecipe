package com.czp.recipe.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.czp.recipe.data.model.FoodRecipeBaseInfo
import com.czp.recipe.data.remote.RemoteRepository
import com.czp.recipe.util.NetworkResult
import com.czp.recipe.util.showToastShort
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    // 网络请求对象
    private val remoteRepository = RemoteRepository()
    // 外部观察数据
    var recipes: MutableLiveData<NetworkResult<FoodRecipeBaseInfo>> = MutableLiveData()


    // 外部通过这个方法发起网络请求
    fun fetchFoodRecipes(type: String) {
        // 判断网络状态
        if (checkConnection()) {
            // 处于loading状态
            recipes.value = NetworkResult.Loading()
            viewModelScope.launch{
                try {
                    val response = remoteRepository.fetchFoodRecipes(type)
                    if (response.isSuccessful) {
                        // 获取数据成功
                        recipes.value = NetworkResult.Success(response.body()!!)
                    }else {
                        // 获取数据失败
                        recipes.value = NetworkResult.Error(response.message())
                        Log.v("dao_fu", "com.czp.recipe.viewmodel.MainViewModel -> fetchFoodRecipes(type: String): Internet error...")
                    }
                }catch (e: Exception) {
                    recipes.value = NetworkResult.Error("time out: ${e.message}")
                }
            }
        } else {
            // 数据库读取
            showToastShort(getApplication(), "No Internet Connection...")
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