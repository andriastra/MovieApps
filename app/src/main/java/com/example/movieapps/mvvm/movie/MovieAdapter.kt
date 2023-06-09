package com.example.movieapps.mvvm.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.base.BaseRecyclerAdapter
import com.example.movieapps.data.model.response.MovieData
import com.example.movieapps.databinding.ItemMovieBinding
import com.example.movieapps.util.Constants.Companion.BASE_URL_IMAGE

class MovieAdapter : BaseRecyclerAdapter<MovieData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        )

    inner class ViewHolder(itemView: View) : BaseViewHolder<MovieData>(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        init {
            binding.movieCv.setOnClickListener {
                onItemClicked?.invoke(masterList[bindingAdapterPosition])

            }
        }

        override fun bind(data: MovieData) {
            with(binding) {
                Glide.with(itemView.context).load("${BASE_URL_IMAGE}${data.posterPath}").into(imageView)
                movieName.text = data.title
            }
        }
    }
}