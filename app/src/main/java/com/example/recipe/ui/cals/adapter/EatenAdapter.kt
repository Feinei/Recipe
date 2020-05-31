package com.example.recipe.ui.cals.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.data.entities.EatenFood
import com.example.recipe.ui.binding.IBindableAdapter

class EatenAdapter(
    private var list: MutableList<EatenFood>,
    private var deleteClickListener: (EatenFood) -> Unit
) : RecyclerView.Adapter<EatenItemHolder>(),
    IBindableAdapter<List<EatenFood>> {

    override fun update(data: List<EatenFood>?) {
        if (data == null) {
            return
        }
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EatenItemHolder =
        EatenItemHolder.create(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: EatenItemHolder, position: Int) {
        holder.bind(list[position], position, deleteClickListener)
    }
}
