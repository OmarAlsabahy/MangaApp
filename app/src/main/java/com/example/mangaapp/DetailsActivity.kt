package com.example.mangaapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.ViewModels.DetailViewModel
import com.example.mangaapp.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    val viewModel : DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val webToonId = intent.getIntExtra("webToonId" , 0)
        viewModel.getWebToon(webToonId)
        viewModel.webToon.observe(this){
           bindData(it)
            binding.progressBar.Gone()
        }

    }

    private fun bindData(webToonModel: WebToonModel) {
        binding.txtTitle.text = webToonModel.title
        binding.rate.text = webToonModel.avergaOfRating.toString()
        binding.description.text = webToonModel.description
        binding.creatorName.text = webToonModel.creator
        Glide.with(this)
            .load(webToonModel.imageUrl)
            .fitCenter()
            .into(binding.webToonImage)
    }
}