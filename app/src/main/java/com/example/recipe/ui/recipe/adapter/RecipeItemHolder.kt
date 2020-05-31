package com.example.recipe.ui.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.data.entities.Recipe
import com.example.recipe.databinding.ItemRecipeBinding

class RecipeItemHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) =
            RecipeItemHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_recipe,
                    parent,
                    false
                )
            )
    }

    fun bind(recipe: Recipe, position: Int, recipeClickListener: (Recipe) -> Unit) {

        binding.recipe = recipe
        binding.root.setOnClickListener { recipeClickListener(recipe) }
        binding.executePendingBindings()
    }
}
