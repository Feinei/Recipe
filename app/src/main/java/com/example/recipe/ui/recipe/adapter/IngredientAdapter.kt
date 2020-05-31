package com.example.recipe.ui.recipe.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.data.entities.Ingredient
import com.example.recipe.ui.binding.IBindableAdapter

class IngredientAdapter(
    private var list: MutableList<Ingredient>
) : RecyclerView.Adapter<IngredientItemHolder>(),
    IBindableAdapter<List<Ingredient>> {

    override fun update(data: List<Ingredient>?) {
        if (data == null) {
            return
        }
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientItemHolder =
        IngredientItemHolder.create(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: IngredientItemHolder, position: Int) {
        holder.bind(list[position], position)
    }
}
