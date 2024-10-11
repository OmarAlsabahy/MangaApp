package com.example.mangaapp.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.R
import com.example.mangaapp.ViewModels.FavouritesViewModel
import com.example.mangaapp.databinding.MangaItemBinding
import com.example.mangaapp.onWebToonClick

class FavouritesAdapter(private val webToons : List<WebToonModel> , private val viewModel:FavouritesViewModel , private val onWebToonClick: onWebToonClick):RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MangaItemBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       if (webToons!=null){
           return webToons.size
       }else{
           return 0
       }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentWebToon = webToons[position]
        holder.bind(currentWebToon)
        //setup favourite button listner
        holder.binding.favourite.setOnClickListener {
            //remove from favourites
            removeFromFavourites(currentWebToon)

        }

        //setup on item clicked
        holder.binding.root.setOnClickListener {
            onWebToonClick.onclick(currentWebToon.id)
        }

    }
    private fun removeFromFavourites(currentWebToonModel: WebToonModel){
        currentWebToonModel.isFavorite = false
        viewModel.removeFromFavourite(currentWebToonModel)
        viewModel.refreshData()
    }


    inner class ViewHolder(val binding:MangaItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(webToonModel: WebToonModel) {
            binding.mangaTitle.text =  webToonModel.title
            binding.rateBar.visibility = View.GONE
            Glide.with(binding.root)
                .load(webToonModel.imageUrl)
                .fitCenter()
                .into(binding.mangaImage)
            if (webToonModel.isFavorite){
                binding.favourite.setImageResource(R.drawable.baseline_favorite_filled)
            }else{
                binding.favourite.setImageResource(R.drawable.baseline_favorite_24)

            }
        }


    }
}