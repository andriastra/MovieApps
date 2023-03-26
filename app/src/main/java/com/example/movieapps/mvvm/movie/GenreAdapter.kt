package com.example.movieapps.mvvm.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.R
import com.example.movieapps.base.BaseRecyclerAdapter
import com.example.movieapps.data.model.response.GenreMovieResp
import com.example.movieapps.databinding.ItemGenreBinding

class GenreAdapter: BaseRecyclerAdapter<GenreMovieResp.Genre>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =

        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_genre, parent, false)
        )

    inner class ViewHolder(itemView: View) : BaseViewHolder<GenreMovieResp.Genre>(itemView) {
        private val binding = ItemGenreBinding.bind(itemView)

        init {
            binding.genreCv.setOnClickListener {
                binding.genreName.setTextColor(it.context.getColor(R.color.primaryColor))
                onItemClicked?.invoke(masterList[bindingAdapterPosition])
            }
        }

        override fun bind(data: GenreMovieResp.Genre) {
            with(binding) {
                genreName.text = data.name
            }
        }
    }
}