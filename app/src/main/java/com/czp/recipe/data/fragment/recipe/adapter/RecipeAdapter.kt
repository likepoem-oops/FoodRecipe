package com.czp.recipe.data.fragment.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.czp.recipe.R
import com.czp.recipe.data.model.Result
import com.czp.recipe.databinding.RecipeItemBinding

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    private var recipeList: List<Result> = emptyList()

    class RecipeViewHolder(private val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return RecipeViewHolder(RecipeItemBinding.inflate(inflater))
            }
        }
        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
            binding.recipeContainer.setOnClickListener {
                binding.recipeContainer.findNavController().navigate(R.id.action_recipeFragment_to_recipeDetailFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    fun setData(newData: List<Result>) {
        recipeList = newData
        notifyDataSetChanged()
    }
}