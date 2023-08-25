package com.czp.recipe.data.fragment.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.czp.recipe.data.model.ExtendedIngredient
import com.czp.recipe.databinding.IngredientItemBinding

class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    private var ingredientList: List<ExtendedIngredient> = emptyList()
    class IngredientViewHolder(val binding: IngredientItemBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(inflater)
                return IngredientViewHolder(binding)
            }
        }
        fun bind(ingredient: ExtendedIngredient) {
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredientList[position])
    }

    fun setData(newData: List<ExtendedIngredient>) {
        ingredientList = newData
        notifyDataSetChanged()
    }
}