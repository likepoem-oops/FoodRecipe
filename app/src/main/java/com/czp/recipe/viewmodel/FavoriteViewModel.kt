package com.czp.recipe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.czp.recipe.data.local.LocalRepository
import com.czp.recipe.data.local.entity.FavoriteEntity
import com.czp.recipe.data.model.Result
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {
    private val localRepository = LocalRepository(application)
    val favoriteRecipe: MutableLiveData<List<FavoriteEntity>> = MutableLiveData()

    // 查询所有收藏的食谱
    fun getAllFavorites() {
        viewModelScope.launch {
            localRepository.getAllFavorites().collect {
                favoriteRecipe.value = it
            }
        }
    }

    // 添加收藏的食谱
    fun insertFavorite(result: Result) {
        viewModelScope.launch{
            val favoriteEntity = FavoriteEntity(0, result)
            localRepository.insertFavorite(favoriteEntity)
        }
    }

    // 删除收藏的食谱
    fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            localRepository.deleteFavorite(favoriteEntity)
        }
    }


}