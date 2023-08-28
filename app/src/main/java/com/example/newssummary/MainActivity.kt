package com.example.newssummary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newssummary.databinding.ActivityMainBinding
import com.example.newssummary.presentation.adapter.NewsAdapter
import com.example.newssummary.presentation.viewmodel.NewsViewModel
import com.example.newssummary.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter:NewsAdapter
    lateinit var viewModel:NewsViewModel

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(
            navController
        )
        viewModel = ViewModelProvider(this, factory)
            .get(NewsViewModel::class.java)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .permitAll()
                .build()
        )
    }
}