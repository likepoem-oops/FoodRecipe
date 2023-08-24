package com.czp.recipe.data.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        initUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    // --------------- UI ---------------
    private fun initUI() {
        binding.detailBtn.isSelected = true
        binding.backBtn.setOnClickListener {

        }
    }

}