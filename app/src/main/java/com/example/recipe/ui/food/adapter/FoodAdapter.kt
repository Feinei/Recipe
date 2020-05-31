package com.example.recipe.ui.food.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.data.entities.Food
import com.example.recipe.ui.binding.IBindableAdapter

class FoodAdapter(
    private var list: MutableList<Food>,
    private val foodClickListener: (Food) -> Unit
) : RecyclerView.Adapter<FoodItemHolder>(),
    IBindableAdapter<List<Food>> {

    override fun update(data: List<Food>?) {
        if (data == null) {
            return
        }
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemHolder =
        FoodItemHolder.create(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FoodItemHolder, position: Int) {
        holder.bind(list[position], position, foodClickListener)
    }
}
