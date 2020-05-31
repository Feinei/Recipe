package com.example.recipe.ui.cals

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipe.R
import com.example.recipe.databinding.FragmentUserDataBinding
import com.example.recipe.ui.MainActivity
import com.example.recipe.utils.Constants
import com.jaredrummler.materialspinner.MaterialSpinner
import com.rengwuxian.materialedittext.MaterialEditText
import com.rengwuxian.materialedittext.validation.RegexpValidator
import kotlinx.android.synthetic.main.fragment_user_data.*
import kotlin.math.roundToInt


class UserDataFragment : Fragment() {

    lateinit var binding: FragmentUserDataBinding

    private var selectedSex: String = Constants.MALE_STRING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserDataBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.btnSave.setOnClickListener(onSubmitButtonListener)

        setSupportActionBar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner_sex.setItems(Constants.MALE_STRING, Constants.FEMALE_STRING)
        spinner_sex.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item ->
            selectedSex = item
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            findNavController().navigate(R.id.action_user_data_to_cals)
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

    private val onSubmitButtonListener = View.OnClickListener {
        if (validateForNumbers(et_weight) &&
            validateForNumbers(et_growth) &&
            validateForNumbers(et_age)
        ) {
            val weight = et_weight.text.toString().toInt()
            val growth = et_growth.text.toString().toInt()
            val age = et_age.text.toString().toInt()
            putDataInSharedPref(weight, growth, age)
            findNavController().navigate(R.id.action_user_data_to_cals)
        }
    }

    private fun validateForNumbers(et: MaterialEditText) =
        et.validateWith(RegexpValidator("Only integer type valid!", "\\d+"))

    private fun setSupportActionBar() {
        (activity as MainActivity).supportActionBar?.title = "Personal data"
        (activity as MainActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun putDataInSharedPref(weight: Int, growth: Int, age: Int) {
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .edit().putInt(
                Constants.OVERALL_CALS,
                (weight * 10 + growth * 6.25 - age * 5 + if (selectedSex == Constants.MALE_STRING) 5 else -161).roundToInt()
            ).apply()
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .edit().putInt(
                Constants.OVERALL_CARBS,
                weight * 5
            ).apply()
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .edit().putInt(
                Constants.OVERALL_PROTEIN,
                (weight * 1.5).roundToInt()
            ).apply()
        (activity as MainActivity).getSharedPreferences(Constants.PREFERENCE, Context.MODE_PRIVATE)
            .edit().putInt(
                Constants.OVERALL_FAT,
                90
            ).apply()
    }
}