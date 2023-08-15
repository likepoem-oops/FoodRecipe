package com.czp.recipe.data.fragment.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.czp.recipe.databinding.ItemTypeBinding

class TypeAdapter: RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {
    // 事件回调
    var adapterCallBack: ((cur: Int, last: Int) -> Unit)? = null
    val typeList = listOf(
        "main course", "side dish", "dessert", "appetizer", "salad",
        "bread", "breakfast", "soup", "beverage", "sauce", "marinade",
        "fingerFood", "snack", "drink")
    private var lastSelectedPosition = 0
    class TypeViewHolder(private val binding: ItemTypeBinding): ViewHolder(binding.root) {
        // 数据回调
        var callBack: ((Int) -> Unit)? = null
        companion object {
            // 创建ViewHolder
            fun from(parent: ViewGroup): TypeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                return TypeViewHolder(ItemTypeBinding.inflate(inflater))
            }
        }
        fun bindTypeText(type: String, position: Int) {
            binding.titleTextView.text = type
            binding.titleTextView.setOnClickListener {
                callBack?.let {
                    it(position)
                }
            }
        }

        fun changItemSelectStatus(status: Boolean) {
            binding.titleTextView.isSelected = status
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val holder = TypeViewHolder.from(parent)
        holder.callBack = {
            // 处理点击之后的回调事件
            if (it != lastSelectedPosition) {
                // 将点击事件传递出去
                adapterCallBack?.let { call ->
                    call(it, lastSelectedPosition)
                    lastSelectedPosition = it
                }
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    private var initTimes = 1;
    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.bindTypeText(typeList[position], position)
        if (position == 0) {
            holder.changItemSelectStatus(true)
        }else {
            holder.changItemSelectStatus(false)
        }
    }
}