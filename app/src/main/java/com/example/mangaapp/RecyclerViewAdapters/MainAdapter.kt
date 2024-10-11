package com.example.mangaapp.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.R
import com.example.mangaapp.Toast
import com.example.mangaapp.ViewModels.MainViewModel
import com.example.mangaapp.databinding.MangaItemBinding
import com.example.mangaapp.onWebToonClick

class MainAdapter(private val webToons:List<WebToonModel> , private val viewModel : MainViewModel , private val onWebToonClick: onWebToonClick, private val toast: Toast):RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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
        holder.bind(webToons[position])
        holder.binding.rateBar.setOnRatingBarChangeListener { _, rating, _ ->
            var currentWebToons = webToons[position]
            currentWebToons.rating = rating
            currentWebToons.numberOfratings++
            currentWebToons.avergaOfRating = (currentWebToons.avergaOfRating + rating)/(currentWebToons.numberOfratings)
            holder.binding.rateBar.rating = 0f
            toast.toastMessage("Rating Added")
            viewModel.updateRate(currentWebToons)
        }


        holder.binding.favourite.setOnClickListener {

          var currentWebToont = webToons[position]

            if (currentWebToont.isFavorite){
                currentWebToont.isFavorite = false
                viewModel.removeFromFavourite(currentWebToont)
            }else{
                currentWebToont.isFavorite = true
                viewModel.addToFavourite(currentWebToont)
            }

            notifyItemChanged(position)
        }

        holder.binding.root.setOnClickListener{
            onWebToonClick.onclick(webToons[position].id)
        }
    }

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