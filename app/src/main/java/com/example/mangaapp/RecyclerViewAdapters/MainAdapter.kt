package com.example.mangaapp.RecyclerViewAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangaapp.Database.Models.WebToonModel
import com.example.mangaapp.ViewModels.MainViewModel
import com.example.mangaapp.databinding.MangaItemBinding

class MainAdapter(private val webToons:List<WebToonModel> , private val viewModel : MainViewModel):RecyclerView.Adapter<MainAdapter.ViewHolder>() {

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
            viewModel.updateRate(currentWebToons)
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
            binding.rateBar.rating = webToonModel.rating
        }




    }

}