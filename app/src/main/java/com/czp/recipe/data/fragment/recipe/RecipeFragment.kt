package com.czp.recipe.data.fragment.recipe

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.czp.recipe.data.fragment.recipe.adapter.RecipeAdapter
import com.czp.recipe.data.fragment.recipe.adapter.TypeAdapter
import com.czp.recipe.data.model.Result
import com.czp.recipe.databinding.FragmentRecipeBinding
import com.czp.recipe.util.NetworkResult
import com.czp.recipe.util.showLog
import com.czp.recipe.util.showToastLong
import com.czp.recipe.util.showToastShort
import com.czp.recipe.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers

class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding

    private val typeAdapter = TypeAdapter()
    private val recipeAdapter = RecipeAdapter()

    private val recipeViewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater)
        recipeViewModel.recipes.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    binding.recipeViewRecyclerView.hideShimmer()
                    recipeAdapter.setData(it.data!!.results)
                    initCommendedView(it.data.results)
                }
                is NetworkResult.Error -> {
                    binding.recipeViewRecyclerView.hideShimmer()
                    showToastShort(requireContext(), it.message!!)
                }
                is NetworkResult.Loading -> {
                    binding.recipeViewRecyclerView.showShimmer()
                }
                else -> {}
            }
        }
        fetchData("main course")

        // shimmer
        initRecipeViewRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化菜谱选择UI
        initRecipeTypeTextRecyclerView()
    }


    // UI init
    private fun initRecipeTypeTextRecyclerView() {
        binding.recipeTextRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), RecyclerView.HORIZONTAL, false)
        binding.recipeTextRecyclerView.adapter = typeAdapter
        // 监听处理回调事件
        typeAdapter.adapterCallBack = { cur, last ->
            val curHolder = binding.recipeTextRecyclerView
                .findViewHolderForAdapterPosition(cur) as TypeAdapter.TypeViewHolder
            var lastHolder = binding.recipeTextRecyclerView
                .findViewHolderForAdapterPosition(last)
            // 选中当前
            curHolder.changItemSelectStatus(true)
            if (lastHolder != null) {
                lastHolder = lastHolder as TypeAdapter.TypeViewHolder
                // 取消选中
                lastHolder.changItemSelectStatus(false)
            }else {
                typeAdapter.notifyItemChanged(last)
            }
            // 获取数据
            fetchData(typeAdapter.typeList[cur])
        }
    }
    private fun initRecipeViewRecyclerView() {
        binding.recipeViewRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recipeAdapter
            showShimmer()
        }
    }
    private fun initCommendedView(result: List<Result>) {
        if (result.isEmpty()) return
        val recommendedRecipe = result[0]
        Glide.with(binding.recommendBg).load(recommendedRecipe.image).into(binding.recommendBg)
        binding.recommendedRecipeName.text = recommendedRecipe.title
        binding.textView5.text = "${recommendedRecipe.readyInMinutes}min"
        binding.textView6.text = recommendedRecipe.aggregateLikes.toString()
    }

    // Data
    private fun fetchData(type: String) {
        recipeViewModel.fetchFoodRecipes(type)
    }
}