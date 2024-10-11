package com.example.mangaapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mangaapp.RecyclerViewAdapters.FavouritesAdapter
import com.example.mangaapp.ViewModels.FavouritesViewModel
import com.example.mangaapp.databinding.ActivityFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesActivity : AppCompatActivity() , onWebToonClick {
    lateinit var binding: ActivityFavouritesBinding
    lateinit var adapter: FavouritesAdapter
    val viewModel : FavouritesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.toolBarColor)))
        viewModel.getFavourites()
        viewModel.webToons.observe(this){
            if (it.isEmpty())
            {
                binding.txtNoItem.Visiblie()
            }else{
                binding.txtNoItem.Gone()
            }
            adapter = FavouritesAdapter(it,viewModel, this)
            binding.favouritesRecycler.adapter = adapter
        }
    }

    override fun onclick(id: Int) {
        val navigateIntent = Intent(this , DetailsActivity::class.java)
        navigateIntent.putExtra("webToonId" , id)
        startActivity(navigateIntent)
    }
}