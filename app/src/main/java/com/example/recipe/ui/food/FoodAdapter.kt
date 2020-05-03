package com.example.recipe.ui.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.example.recipe.data.db.entities.Food
import com.example.recipe.utils.UtilMethods
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_food.*

class FoodAdapter(
    private val itemClickLambda: (Food) -> Unit
) : ListAdapter<Food, FoodAdapter.ItemHolder>(object : DiffUtil.ItemCallback<Food>() {

    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
        oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder =
        ItemHolder.create(
            parent,
            itemClickLambda
        )

    override fun onBindViewHolder(holder: ItemHolder, position: Int) =
        holder.bind(getItem(position))


    class ItemHolder(
        override val containerView: View,
        private val clickLambda: (Food) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        companion object {
            fun create(parent: ViewGroup, clickLambda: (Food) -> Unit) =
                ItemHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.item_food,
                        parent,
                        false
                    ),
                    clickLambda
                )
        }

        fun bind(food: Food) {

            // binding?
            var foodName = food.foodName
            foodName[0].toUpperCase()
            if (food.foodName.length >= 17)
                foodName = foodName.take(17) + "..."
            tv_name.text = foodName

            tv_cals.text = itemView.context.getString(
                R.string.cals_param,
                UtilMethods.roundDoubleToOneDigit(food.cals).toString()
            )
            tv_carbs.text = itemView.context.getString(
                R.string.carbs_param,
                UtilMethods.roundDoubleToOneDigit(food.carbs).toString()
            )
            tv_fat.text = itemView.context.getString(
                R.string.fat_param,
                UtilMethods.roundDoubleToOneDigit(food.fat).toString()
            )
            tv_protein.text = itemView.context.getString(
                R.string.protein_param,
                UtilMethods.roundDoubleToOneDigit(food.protein).toString()
            )

            iv_icon.setImageResource(R.drawable.ic_cake_black_24dp)

            itemView.setOnClickListener {
                clickLambda(food)
            }
        }
    }
}
