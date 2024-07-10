package com.example.effectivemobile.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController

        if (navController != null) {
            binding.bottomNavigation.setupWithNavController(navController)
        } else {
            throw IllegalStateException("NavController не найден")
        }
    }


//    private fun observeMenu() {
//        binding.bottomNavigation.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_tickets -> {
//                    val fragment = MainFragment.newInstance()
//                    val transaction = supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.nav_host_fragment, fragment)
//                    transaction.commit()
//                    true
//                }
//
//                else -> {
//                    loadFragmentPlaceholder()
//                    true
//                }
//            }
//        }
//    }

//    private fun loadFragmentPlaceholder() {
//        val fragment = PlaceholderFragmentMenu.newInstance()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.main_container, fragment)
//        transaction.commit()
//    }

}