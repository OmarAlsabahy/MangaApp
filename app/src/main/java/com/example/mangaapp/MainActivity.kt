package com.example.mangaapp

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mangaapp.RecyclerViewAdapters.HomeAdapter
import com.example.mangaapp.ViewModels.HomeViewModel
import com.example.mangaapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , onWebToonClick , Toast{
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: HomeAdapter
    val viewModel:HomeViewModel by viewModels()
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
        //set toolbar color
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(getColor(R.color.toolBarColor)))

        //set adapter
        viewModel.initializeData()
        viewModel.webToonModelList.observe(this){
            adapter = HomeAdapter(it , viewModel , this,this)
            binding.webToonsRecycler.adapter = adapter
            binding.progressBar.Gone()
        }

    }

    //refresh data when returns from details activity
    override fun onResume() {
        super.onResume()
        viewModel.refreshData()
    }

    //create menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.main_toolbar, menu)
        return  true
    }

    //navigate to favourites activity on selecting favourite from menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.favourites){
            val navigateIntent = Intent(this , FavouritesActivity::class.java)
            startActivity(navigateIntent)

        }
        return true
    }

    //navigate to details activity on clicking webtoon

    override fun onclick(id: Int) {
        val navigateIntent = Intent(this , DetailsActivity::class.java)
        navigateIntent.putExtra("webToonId" , id)
        startActivity(navigateIntent)
    }

    //show toast message
    override fun toastMessage(message: String) {
        showToast(message)
    }


}