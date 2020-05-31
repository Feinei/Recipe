package com.example.recipe.ui.navigation.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipe.databinding.FragmentCaloriesContainerBinding

class CaloriesContainerFragment : Fragment() {

    private lateinit var binding: FragmentCaloriesContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCaloriesContainerBinding.inflate(inflater, container, false)
        return binding.root
    }
}
