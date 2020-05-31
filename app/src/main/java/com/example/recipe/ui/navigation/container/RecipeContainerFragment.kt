package com.example.recipe.ui.navigation.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipe.databinding.FragmentRecipeContainerBinding

class RecipeContainerFragment : Fragment() {

    private lateinit var binding: FragmentRecipeContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeContainerBinding.inflate(inflater, container, false)
        return binding.root
    }
}
