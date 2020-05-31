package com.example.recipe.ui.navigation.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recipe.databinding.FragmentFridgeContainerBinding

class FridgeContainerFragment : Fragment() {

    private lateinit var binding: FragmentFridgeContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFridgeContainerBinding.inflate(inflater, container, false)
        return binding.root
    }
}
