package com.example.recipe.ui.fridge.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.ui.binding.IBindableAdapter

class FridgeAdapter(
    private var list: MutableList<FridgeFood>,
    private var deleteClickListener: (FridgeFood) -> Unit
) : RecyclerView.Adapter<FridgeItemHolder>(),
    IBindableAdapter<List<FridgeFood>> {

    override fun update(data: List<FridgeFood>?) {
        if (data == null) {
            return
        }
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FridgeItemHolder =
        FridgeItemHolder.create(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FridgeItemHolder, position: Int) {
        holder.bind(list[position], position, deleteClickListener)
    }
}
