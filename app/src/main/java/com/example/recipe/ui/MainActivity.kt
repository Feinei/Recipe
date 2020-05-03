package com.example.recipe.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.recipe.R
import com.example.recipe.ui.food.FoodListFragment
import com.example.recipe.ui.cals.CaloriesFragment
import com.example.recipe.ui.recipes.RecipesFragment
import dagger.android.DispatchingAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import dagger.android.support.HasSupportFragmentInjector

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    companion object {
        private const val DEFAULT_FRAGMENT_TAG = "default"
        private const val RECYCLER_FRAGMENT_TAG = "recycler_frag"
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        bnv_main.setOnNavigationItemReselectedListener {
            // var tag: String? = DEFAULT_FRAGMENT_TAG
            var fragment: Fragment = when (it.itemId) {
                R.id.nav_cal -> CaloriesFragment.newInstance()
                R.id.nav_fridge -> FoodListFragment.newInstance() // test fragment
                R.id.nav_recipe -> RecipesFragment.newInstance()
                else -> CaloriesFragment.newInstance()
            }
            fragment?.let { currentFragment ->
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, currentFragment).commit()
            }
        }
    }
}
