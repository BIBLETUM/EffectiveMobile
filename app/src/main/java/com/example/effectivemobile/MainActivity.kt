package com.example.effectivemobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.effectivemobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = MainFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.mainContainer.id, fragment).commit()

        val modalBottomSheet = BottomSheetSearch()
        modalBottomSheet.show(supportFragmentManager, BottomSheetSearch.TAG)
    }
}