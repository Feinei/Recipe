package com.example.recipe.ui.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.recipe.App
import com.example.recipe.R
import com.example.recipe.di.utils.Injectable
import com.example.recipe.di.utils.injectViewModel
import com.example.recipe.utils.Status
import kotlinx.android.synthetic.main.fragment_food_list.*
import javax.inject.Inject

class FoodListFragment() : Fragment(), Injectable {

    companion object {
        fun newInstance() = FoodListFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FoodListViewModel
    private lateinit var adapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        App.addFoodComponent()
        App.foodComponent.inject(this)
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //!!!
        viewModel = injectViewModel(viewModelFactory)

        adapter = FoodAdapter { }
        adapter.submitList(arrayListOf())
        rv_food.adapter = adapter

        viewModel.fetchFoodName("a")
        viewModel.food.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { adapter.submitList(it) }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        })
    }
}