package com.example.mangaapp.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.R
import com.example.mangaapp.Toast
import com.example.mangaapp.ViewModels.HomeViewModel
import com.example.mangaapp.databinding.MangaItemBinding
import com.example.mangaapp.onWebToonClick

class HomeAdapter(private val webToons:List<WebToonModel>, private val viewModel : HomeViewModel, private val onWebToonClick: onWebToonClick, private val toast: Toast):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MangaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
        //bind data to view holder
        holder.bind(webToons[position])

        //check if the rating bar is being reset
        var isResetting = false
        //set on rating change listener
        holder.binding.rateBar.setOnRatingBarChangeListener { _, rating, _ ->
            if (!isResetting)
            {
                val currentWebToons = webToons[position]
                updateRate(currentWebToons, rating)
                isResetting = true
                resetToDefault(holder)
                isResetting = false
            }

        }

        //set on favourite click listener
        holder.binding.favourite.setOnClickListener {

          var currentWebToont = webToons[position]

            addToFavourite(currentWebToont)

            notifyItemChanged(position)
        }

        holder.binding.root.setOnClickListener{
            onWebToonClick.onclick(webToons[position].id)
        }
    }
    //check if webToon is already in favourites or not then add or remove it

    private fun addToFavourite(currentWebToon: WebToonModel) {
        if (currentWebToon.isFavorite){
            removeFromFavourite(currentWebToon)
        }else{
            currentWebToon.isFavorite = true
            viewModel.addToFavourite(currentWebToon)
        }
    }

    //remove webtoon from favourites

    private fun removeFromFavourite(currentWebToon: WebToonModel) {
        currentWebToon.isFavorite = false
        viewModel.removeFromFavourite(currentWebToon)
        viewModel.refreshData()
    }

    //reset the rating bar to default

    private fun resetToDefault(holder: ViewHolder) {
        holder.binding.rateBar.rating = 0f
    }

    //calculate and update average rating
    private fun updateRate(currentWebToons: WebToonModel, rating: Float) {
        currentWebToons.rating = rating
        currentWebToons.numberOfratings++
        currentWebToons.avergaOfRating = (currentWebToons.avergaOfRating+rating)/currentWebToons.numberOfratings
        toast.toastMessage("Rating Added")
        viewModel.updateRate(currentWebToons)
    }

    //creating recycler view view holder
    inner class ViewHolder(val binding: MangaItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(webToonModel: WebToonModel) {
            binding.mangaTitle.text = webToonModel.title
            Glide.with(binding.root)
                .load(webToonModel.imageUrl)
                .override(binding.mangaImage.width , binding.mangaImage.height)
                .fitCenter()
                .into(binding.mangaImage)
            if (webToonModel.isFavorite){
                binding.favourite.setImageResource(R.drawable.baseline_favorite_filled)
            }
        }


    }

}