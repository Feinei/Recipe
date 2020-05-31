package com.example.recipe.ui.recipe.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.data.entities.Recipe
import com.example.recipe.ui.binding.IBindableAdapter

class RecipeAdapter(
    private var list: MutableList<Recipe>,
    private val recipeClickListener: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeItemHolder>(),
    IBindableAdapter<List<Recipe>> {

    override fun update(data: List<Recipe>?) {
        if (data == null) {
            return
        }
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemHolder =
        RecipeItemHolder.create(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecipeItemHolder, position: Int) {
        holder.bind(list[position], position, recipeClickListener)
    }
}
