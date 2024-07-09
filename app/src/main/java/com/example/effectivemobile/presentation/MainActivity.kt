package com.example.effectivemobile.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.effectivemobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = SearchFeaturedTicketsFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.mainContainer.id, fragment).commit()

        val modalBottomSheet = BottomSheetSearch()
        modalBottomSheet.show(supportFragmentManager, BottomSheetSearch.TAG)
    }
}