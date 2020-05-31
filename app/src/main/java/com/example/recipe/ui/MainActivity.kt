package com.example.recipe.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.plusAssign
import androidx.navigation.ui.setupWithNavController
import com.example.recipe.R
import com.example.recipe.di.utils.AppInjector
import com.example.recipe.ui.navigation.KeepStateNavigator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppInjector.injectMainActivity(this)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.fragment_nav)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_nav)
        val navigator =
            KeepStateNavigator(this, navHostFragment!!.childFragmentManager, R.id.fragment_nav)

        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.main_navigation)

        btm_nv.setupWithNavController(navController)
    }

    fun showBottomNavigation() {
        btm_nv.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        btm_nv.visibility = View.GONE
    }
}
