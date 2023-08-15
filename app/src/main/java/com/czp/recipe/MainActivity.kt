package com.czp.recipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.czp.recipe.data.remote.UrlString
import com.czp.recipe.databinding.ActivityMainBinding
import com.czp.recipe.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    // 导航栏/菜单栏参数
    private lateinit var barConfiguration: AppBarConfiguration

    val urlString = "https://api.spoonacular.com/recipes/complexSearch?apiKey=a00ff51ceb9f4b15959db96db2429eaf&addRecipeInformation=true&number=1&cuisine=Chinese&fillIngredients=true"
    val apiKey = "a00ff51ceb9f4b15959db96db2429eaf"
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val naviHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = naviHostFragment.navController

        barConfiguration = AppBarConfiguration(
            setOf(R.id.recipeFragment, R.id.favoriteFragment, R.id.otherFragment)
        )
        // setupActionBarWithNavController(navController, barConfiguration)

        binding.bottomNavigationView.setupWithNavController(navController)
    }


}