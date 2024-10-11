package com.example.mangaapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mangaapp.RecyclerViewAdapters.MainAdapter
import com.example.mangaapp.ViewModels.MainViewModel
import com.example.mangaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , onWebToonClick , Toast{
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: MainAdapter
    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel.initializeData()
        viewModel.webToonModelList.observe(this){
            adapter = MainAdapter(it , viewModel , this,this)
            binding.webToonsRecycler.adapter = adapter
            binding.progressBar.Gone()
        }

    }

    override fun onclick(id: Int) {
        val navigateIntent = Intent(this , DetailsActivity::class.java)
        navigateIntent.putExtra("webToonId" , id)
        startActivity(navigateIntent)
    }

    override fun toastMessage(message: String) {
        showToast(message)
    }
}