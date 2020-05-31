package com.example.recipe.ui.recipe

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.data.entities.Recipe
import com.example.recipe.databinding.FragmentRecipeDetailBinding
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.MainActivity
import com.example.recipe.ui.recipe.adapter.IngredientAdapter
import com.example.recipe.utils.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import javax.inject.Inject

class RecipeDetailFragment : Fragment() {

    lateinit var adapter: IngredientAdapter
    lateinit var binding: FragmentRecipeDetailBinding
    lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipe = Gson().fromJson(arguments?.getString(Constants.KEY_RECIPE), Recipe::class.java)

        adapter = IngredientAdapter(mutableListOf())
        adapter.update(recipe.ingredients)

        binding = FragmentRecipeDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvIngr.adapter = adapter
        binding.recipe = recipe
        binding.btnSource.setOnClickListener(onSourceClickListener)

        setSupportActionBar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipe = Gson().fromJson(arguments?.getString(Constants.KEY_RECIPE), Recipe::class.java)
        adapter.update(recipe.ingredients)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            findNavController().navigate(R.id.action_detail_to_recipe_list)
        return super.onOptionsItemSelected(item)
    }

    private val onSourceClickListener = View.OnClickListener {
        web.settings.javaScriptEnabled = true
        web.loadUrl(recipe.recipeSource)
    }

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Recipe"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
