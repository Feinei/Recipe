package com.example.recipe.ui.fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.data.entities.FridgeFood
import com.example.recipe.databinding.FragmentFridgeBinding
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.MainActivity
import com.example.recipe.ui.fridge.adapter.FridgeAdapter
import com.example.recipe.utils.Constants
import javax.inject.Inject

class FridgeFragment() : Fragment() {

    @Inject
    lateinit var viewModel: FridgeViewModel

    lateinit var binding: FragmentFridgeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.plusFridgeComponent().inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val adapter = FridgeAdapter(mutableListOf(), deleteFoodClickListener)

        binding = FragmentFridgeBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvFridge.adapter = adapter
        binding.fridgeViewModel = viewModel
        binding.btnAdd.setOnClickListener(onAddButtonListener)

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
        AppInjector.clearFridgeComponent()
    }

    private val onAddButtonListener = View.OnClickListener {
        val bundle = Bundle()
        bundle.putString(Constants.KEY_FROM, Constants.FROM_FRIDGE)
        findNavController().navigate(R.id.action_fridge_to_choice, bundle)
    }

    private val deleteFoodClickListener = { food: FridgeFood ->
        viewModel.deleteFoodById(food.id)
        viewModel.loadFood()
    }

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Fridge"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
