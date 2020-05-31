package com.example.recipe.ui.food.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.data.entities.Food
import com.example.recipe.databinding.ItemFoodBinding

class FoodItemHolder(val binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            FoodItemHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_food,
                    parent,
                    false
                )
            )
    }

    fun bind(food: Food, position: Int, foodClickListener: (Food) -> Unit) {

        binding.food = food
        binding.root.setOnClickListener { foodClickListener(food) }
        binding.executePendingBindings()
    }
}
