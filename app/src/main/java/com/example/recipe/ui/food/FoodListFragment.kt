package com.example.recipe.ui.food

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.data.entities.Food
import com.example.recipe.databinding.FragmentFoodListBinding
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.MainActivity
import com.example.recipe.ui.food.adapter.FoodAdapter
import com.example.recipe.utils.Constants
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.validation.RegexpValidator
import kotlinx.android.synthetic.main.fragment_food_list.*
import javax.inject.Inject

class FoodListFragment() : Fragment() {

    @Inject
    lateinit var viewModel: FoodListViewModel

    lateinit var binding: FragmentFoodListBinding

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.plusFoodComponent().inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = FoodAdapter(mutableListOf(), onFoodClickListener)

        binding = FragmentFoodListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvFood.adapter = adapter
        binding.foodListViewModel = viewModel

        setSupportActionBar()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearFoodComponent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            if (arguments?.getString(Constants.KEY_FROM) == Constants.FROM_FRIDGE)
                findNavController().navigate(R.id.action_food_list_to_fridge)
            else
                findNavController().navigate(R.id.action_food_list_to_cals)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            setOnQueryTextListener(queryListener)
        }

        val menuItem = menu.findItem(R.id.search)
        menuItem.setOnActionExpandListener(onActionExpandListener)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private val queryListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(text: String?): Boolean {
            text?.let { viewModel.getFood(it) }
            return true
        }

        override fun onQueryTextChange(text: String?): Boolean {
            return false
        }
    }

    private val onActionExpandListener = object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            binding.rvFood.visibility = View.GONE
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            binding.rvFood.visibility = View.VISIBLE
            return true
        }
    }

    private val onFoodClickListener = { food: Food ->
        if (validateForNumbers(et_grams)) {
            viewModel.addFoodToDestination(
                arguments?.getString(Constants.KEY_FROM),
                food,
                et_grams.text.toString().toInt()
            )
            if (arguments?.getString(Constants.KEY_FROM) == Constants.FROM_FRIDGE)
                findNavController().navigate(R.id.action_food_list_to_fridge)
            else
                findNavController().navigate(R.id.action_food_list_to_cals)
        }
    }

    private fun validateForNumbers(et: MaterialEditText) =
        et.validateWith(RegexpValidator("Only number type valid!", "\\d+"))

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Food choice"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
