package com.czp.recipe.data.fragment.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.czp.recipe.data.fragment.recipe.adapter.RecipeAdapter
import com.czp.recipe.data.fragment.recipe.adapter.TypeAdapter
import com.czp.recipe.data.model.Result
import com.czp.recipe.databinding.FragmentRecipeBinding
import com.czp.recipe.viewmodel.MainViewModel

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
            if (it.results.isNotEmpty()) {
                // 结束Shimmer加载效果
                binding.recipeViewRecyclerView.hideShimmer()
                // 传递下载数据
                recipeAdapter.setData(it.results)
                // 初始化推荐菜单
                initCommendedView(it.results)
            } else {
                // 显示无结果提示

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
        val recommendedRecipe = result[0]
        Glide.with(binding.recommendBg).load(recommendedRecipe.image).into(binding.recommendBg)
        binding.recommendedRecipeName.text = recommendedRecipe.title
        binding.textView5.text = "${recommendedRecipe.readyInMinutes.toString()}min"
        binding.textView6.text = recommendedRecipe.aggregateLikes.toString()
    }

    // Data
    private fun fetchData(type: String) {
        recipeViewModel.fetchFoodRecipes(type)
    }
}