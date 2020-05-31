package com.example.recipe.ui.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.data.entities.Ingredient
import com.example.recipe.databinding.ItemIngredientBinding

class IngredientItemHolder(val binding: ItemIngredientBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            IngredientItemHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_ingredient,
                    parent,
                    false
                )
            )
    }

    fun bind(ingr: Ingredient, position: Int) {

        binding.ingr = ingr
        binding.executePendingBindings()
    }
}
