package com.czp.recipe.data.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.czp.recipe.R
import com.czp.recipe.data.fragment.recipe.adapter.IngredientAdapter
import com.czp.recipe.data.model.ExtendedIngredient
import com.czp.recipe.databinding.FragmentIngredientBinding


class IngredientFragment(private val ingredients: List<ExtendedIngredient>) : Fragment() {

    private lateinit var binding: FragmentIngredientBinding

    private val ingredientAdapter = IngredientAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentIngredientBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    // ---------- UI ----------
    private fun initUI() {
        binding.ingredientRecyclerView.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        binding.ingredientRecyclerView.adapter = ingredientAdapter
        ingredientAdapter.setData(ingredients)
    }
}