package com.example.recipe.ui.general

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.databinding.FragmentAddNewFoodBinding
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.MainActivity
import com.example.recipe.utils.Constants
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.validation.METValidator
import com.rengwuxian.materialedittext.validation.RegexpValidator
import kotlinx.android.synthetic.main.fragment_add_new_food.*
import kotlinx.android.synthetic.main.fragment_food_list.*
import javax.inject.Inject

class AddNewFoodFragment() : Fragment() {

    @Inject
    lateinit var viewModel: AddNewFoodViewModel

    lateinit var binding: FragmentAddNewFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AppInjector.plusAddNewFoodComponent().inject(this)
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNewFoodBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnAdd.setOnClickListener(onAddClickListener)

        setSupportActionBar()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        AppInjector.clearAddNewFoodComponent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            if (arguments?.getString(Constants.KEY_FROM) == Constants.FROM_FRIDGE)
                findNavController().navigate(R.id.action_new_food_to_fridge)
            else
                findNavController().navigate(R.id.action_new_food_to_cals)
        return super.onOptionsItemSelected(item)
    }

    private val onAddClickListener = View.OnClickListener {

        if (validateForNumbers(et_cals) &&
            validateForNumbers(et_carbo) &&
            validateForNumbers(et_protein) &&
            validateForNumbers(et_fat) &&
            validateForNumbers(et_grams_new)
        ) {
            viewModel.addFoodToDestination(
                arguments?.getString(Constants.KEY_FROM),
                et_product_name.text.toString(),
                et_cals.text.toString(),
                et_carbo.text.toString(),
                et_protein.text.toString(),
                et_fat.text.toString(),
                et_grams_new.text.toString()
            )
            if (arguments?.getString(Constants.KEY_FROM) == Constants.FROM_FRIDGE)
                findNavController().navigate(R.id.action_new_food_to_fridge)
            else
                findNavController().navigate(R.id.action_new_food_to_cals)
        }
    }

    private fun validateForNumbers(et: MaterialEditText) =
        et.validateWith(RegexpValidator("Only number type valid!", "\\d+"))

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "New food"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
