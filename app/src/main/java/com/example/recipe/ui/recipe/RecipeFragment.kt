package com.example.recipe.ui.recipe

import android.app.SearchManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipe.R
import com.example.recipe.data.entities.Recipe
import com.example.recipe.databinding.FragmentRecipeBinding
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_recipe.*
import javax.inject.Inject
import android.content.Context
import com.example.recipe.ui.recipe.adapter.RecipeAdapter
import com.example.recipe.utils.Constants
import com.google.gson.Gson

class RecipeFragment : Fragment() {

    @Inject
    lateinit var viewModel: RecipeViewModel

    lateinit var binding: FragmentRecipeBinding

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.plusRecipeComponent().inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = RecipeAdapter(mutableListOf(), recipeClickListener)

        binding = FragmentRecipeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvRecipe.layoutManager =
            GridLayoutManager((activity as MainActivity).applicationContext, 2)
        binding.rvRecipe.adapter = adapter
        binding.recipeListViewModel = viewModel

        setSupportActionBar()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearRecipeComponent()
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
            text?.let { viewModel.getRecipes(it) }
            return true
        }

        override fun onQueryTextChange(text: String?): Boolean {
            return false
        }
    }

    private val onActionExpandListener = object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
            binding.rvRecipe.visibility = View.GONE
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            binding.rvRecipe.visibility = View.VISIBLE
            return true
        }
    }

    private val recipeClickListener = { recipe: Recipe ->
        val bundle = Bundle()
        bundle.putString(Constants.KEY_RECIPE, Gson().toJson(recipe))
        findNavController().navigate(R.id.action_recipe_to_detail, bundle)
    }

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Recipes"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
