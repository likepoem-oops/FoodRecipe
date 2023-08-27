package com.czp.recipe.data.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.czp.recipe.data.fragment.recipe.adapter.DetailViewPagerAdapter
import com.czp.recipe.databinding.FragmentRecipeDetailBinding
import com.czp.recipe.viewmodel.FavoriteViewModel


class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    // 接收传参
    private val recipeArgs: RecipeDetailFragmentArgs by navArgs()
    // 收藏食谱
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        favoriteViewModel.getAllFavorites()
    }

    // --------------- UI ---------------
    private fun initUI() {
        binding.detailBtn.isSelected = true
        // 传入数据
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        initClickEvent()
        initViewPager()
        favoriteViewModel.favoriteRecipe.observe(viewLifecycleOwner) {
            it.forEach { entity ->
                if (entity.recipe == recipeArgs.recipe) {
                    binding.collectBtn.isSelected = true
                    return@forEach
                }
            }
        }
    }

    private fun initClickEvent() {
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.detailBtn.setOnClickListener {
            selectDetail()
            binding.viewPager.currentItem = 0
        }
        binding.ingredientBtn.setOnClickListener {
            selectIngredient()
            binding.viewPager.currentItem = 1
        }
        binding.viewPager.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback() {
                // onPageScrolled 可以通过重写这个方法来实现同步拖拽动画
                // 选择
                override fun onPageSelected(position: Int) {
                    if (position == 0) {
                        selectDetail()
                    }else {
                        selectIngredient()
                    }
                }
            }
        )
        binding.collectBtn.setOnClickListener {
            if (binding.collectBtn.isSelected) {
                // 从数据库删除食谱
                favoriteViewModel.favoriteRecipe.value?.forEach { entity ->
                    if (entity.recipe == recipeArgs.recipe) {
                        favoriteViewModel.deleteFavorite(entity)
                        binding.collectBtn.isSelected = false
                    }
                }
            }else {
                // 将这个食谱插入到收藏中
                favoriteViewModel.insertFavorite(recipeArgs.recipe)
            }
        }
    }
    private fun selectDetail() {
        if (!binding.detailBtn.isSelected) {
            binding.detailBtn.isSelected = true
            binding.ingredientBtn.isSelected = false
            indicatorAnim(0f)
        }
    }
    private fun selectIngredient() {
        if (!binding.ingredientBtn.isSelected) {
            binding.ingredientBtn.isSelected = true
            binding.detailBtn.isSelected = false
            val moveDis = binding.ingredientBtn.x - binding.detailBtn.x
            indicatorAnim(moveDis)
        }
    }

    // 初始化ViewPager
    private fun initViewPager() {
        val fragments = listOf(
            SummaryFragment(recipeArgs.recipe.summary),
            IngredientFragment(recipeArgs.recipe.extendedIngredients)
        )
        binding.viewPager.adapter = DetailViewPagerAdapter(
            fragments,
            requireActivity().supportFragmentManager,
            lifecycle)
    }

    // --------------- anim ---------------
    private fun indicatorAnim(value: Float) {
        binding.indicatorView.animate()
            .translationX(value)
            .setDuration(300)
            .start()
    }
}