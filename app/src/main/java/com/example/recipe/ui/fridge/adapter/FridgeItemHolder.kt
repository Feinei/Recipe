package com.example.recipe.ui.fridge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.databinding.ItemFridgeFoodBinding

class FridgeItemHolder(val binding: ItemFridgeFoodBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            FridgeItemHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_fridge_food,
                    parent,
                    false
                )
            )
    }

    fun bind(food: FridgeFood, position: Int, deleteClickListener: (FridgeFood) -> Unit) {

        binding.food = food
        binding.btnDelete.setOnClickListener { deleteClickListener(food) }
        binding.executePendingBindings()
    }
}
