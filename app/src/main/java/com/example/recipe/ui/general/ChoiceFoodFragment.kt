package com.example.recipe.ui.general

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.databinding.FragmentChoiceFoodBinding
import com.example.recipe.ui.MainActivity
import com.example.recipe.utils.Constants

class ChoiceFoodFragment() : Fragment() {

    lateinit var binding: FragmentChoiceFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChoiceFoodBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnChoice.setOnClickListener(onChoiceButtonListener)
        binding.btnNew.setOnClickListener(onNewButtonListener)

        setSupportActionBar()

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            if (arguments?.getString(Constants.KEY_FROM) == Constants.FROM_FRIDGE)
                findNavController().navigate(R.id.action_choice_to_fridge)
            else
                findNavController().navigate(R.id.action_choice_to_cals)
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).hideBottomNavigation()
    }

    override fun onDetach() {
        (activity as MainActivity).showBottomNavigation()
        super.onDetach()
    }

    private val onChoiceButtonListener = View.OnClickListener {
        val bundle = Bundle()
        bundle.putString(Constants.KEY_FROM, arguments?.getString(Constants.KEY_FROM))
        findNavController().navigate(R.id.action_choice_to_food_list, bundle)
    }

    private val onNewButtonListener = View.OnClickListener {
        val bundle = Bundle()
        bundle.putString(Constants.KEY_FROM, arguments?.getString(Constants.KEY_FROM))
        findNavController().navigate(R.id.action_choice_to_new_food, bundle)
    }

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}