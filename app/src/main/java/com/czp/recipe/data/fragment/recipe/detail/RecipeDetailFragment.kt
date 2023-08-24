package com.czp.recipe.data.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import com.czp.recipe.R
import com.czp.recipe.databinding.FragmentRecipeDetailBinding


class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    // 接收传参
    private val recipeArgs: RecipeDetailFragmentArgs by navArgs()
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
    }

    // --------------- UI ---------------
    private fun initUI() {
        binding.detailBtn.isSelected = true
        // 传入数据
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.detailBtn.setOnClickListener {
            if (!binding.detailBtn.isSelected) {
                binding.detailBtn.isSelected = true
                binding.ingredientBtn.isSelected = false
                indicatorAnim(0f)
            }
        }
        binding.ingredientBtn.setOnClickListener {
            if (!binding.ingredientBtn.isSelected) {
                binding.ingredientBtn.isSelected = true
                binding.detailBtn.isSelected = false
                val moveDis = binding.ingredientBtn.x - binding.detailBtn.x
                indicatorAnim(moveDis)
            }
        }
    }

    // --------------- anim ---------------
    private fun indicatorAnim(value: Float) {
        binding.indicatorView.animate()
            .translationX(value)
            .setDuration(300)
            .start()
    }
}