package com.example.recipe.ui.cals

import android.content.Context
import android.drm.DrmStore
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.data.entities.EatenFood
import com.example.recipe.data.entities.Nutrients
import com.example.recipe.databinding.FragmentCaloriesBinding
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.MainActivity
import com.example.recipe.ui.cals.adapter.EatenAdapter
import com.example.recipe.utils.Constants
import javax.inject.Inject

class CaloriesFragment() : Fragment() {

    @Inject
    lateinit var viewModel: CaloriesViewModel

    lateinit var binding: FragmentCaloriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.plusCaloriesComponent().inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        checkForFirstLaunch()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = EatenAdapter(mutableListOf(), deleteFoodClickListener)

        binding = FragmentCaloriesBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvEaten.adapter = adapter
        binding.caloriesViewModel = viewModel
        binding.btnAddEaten.setOnClickListener(onAddButtonListener)
        binding.overalNutrients = getOverallNutrients()

        setSupportActionBar()

        viewModel.loadFood()
        return binding.root
    }

    override fun onResume() {
        viewModel.loadFood()
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearCaloriesComponent()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings_menu, menu)

        menu.findItem(R.id.settings).setOnMenuItemClickListener {
            findNavController().navigate(R.id.action_cals_to_user_data)
            true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private val onAddButtonListener = View.OnClickListener {
        val bundle = Bundle()
        bundle.putString(Constants.KEY_FROM, Constants.FROM_CALS)
        findNavController().navigate(R.id.action_cals_to_choice, bundle)
    }

    private val deleteFoodClickListener = { food: EatenFood ->
        viewModel.deleteFoodById(food.id)
    }

    private fun checkForFirstLaunch() {
        val isFirstRun =
            (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
                .getBoolean(Constants.IS_FIRST_RUN, true)
        if (isFirstRun) {
            findNavController().navigate(R.id.action_cals_to_user_data)
        }
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE).edit()
            .putBoolean(Constants.IS_FIRST_RUN, false).apply()
    }

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Today nutrients"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun getOverallNutrients() = Nutrients(
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .getInt(Constants.OVERALL_CALS, 2000).toDouble(),
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .getInt(Constants.OVERALL_CARBS, 100).toDouble(),
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .getInt(Constants.OVERALL_PROTEIN, 75).toDouble(),
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .getInt(Constants.OVERALL_FAT, 90).toDouble()
    )
}
