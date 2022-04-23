package com.njuacha.searchflickr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.njuacha.searchflickr.R
import com.njuacha.searchflickr.databinding.SearchItemBinding
import com.njuacha.searchflickr.model.Photo
import com.squareup.picasso.Picasso

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var photos = emptyList<Photo>()

    fun setPhotos(photos: List<Photo>) {
        this.photos = photos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val searchItemBinding =
            SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(searchItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    class ViewHolder(private val searchItemBinding: SearchItemBinding) :
        RecyclerView.ViewHolder(searchItemBinding.root) {

        fun bind(photo: Photo) {
            val url =
                "https://farm${photo.farm}.static.flickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
            Picasso.with(searchItemBinding.root.context).load(url).placeholder(R.mipmap.ic_launcher)
                .into(searchItemBinding.searchItemImage)
        }
    }
}
