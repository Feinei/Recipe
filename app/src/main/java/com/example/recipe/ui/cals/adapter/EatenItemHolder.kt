package com.example.recipe.ui.cals.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.data.entities.EatenFood
import com.example.recipe.databinding.ItemCalsFoodBinding

class EatenItemHolder(val binding: ItemCalsFoodBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            EatenItemHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_cals_food,
                    parent,
                    false
                )
            )
    }

    fun bind(food: EatenFood, position: Int, deleteClickListener: (EatenFood) -> Unit) {

        binding.food = food
        binding.btnDelete.setOnClickListener { deleteClickListener(food) }
        binding.executePendingBindings()
    }
}
