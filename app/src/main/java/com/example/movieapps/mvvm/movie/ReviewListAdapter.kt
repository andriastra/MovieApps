package com.example.movieapps.mvvm.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.base.BaseRecyclerAdapter
import com.example.movieapps.data.model.response.MovieData
import com.example.movieapps.data.model.response.ReviewData
import com.example.movieapps.databinding.ItemMovieBinding
import com.example.movieapps.databinding.ItemReviewBinding
import com.example.movieapps.util.Constants

class ReviewListAdapter : BaseRecyclerAdapter<ReviewData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_review, parent, false)
        )

    inner class ViewHolder(itemView: View) : BaseViewHolder<ReviewData>(itemView) {

        private val binding = ItemReviewBinding.bind(itemView)

        init {

        }

        override fun bind(data: ReviewData) {
            with(binding) {
                itemReviewTitle.text = data.author
                itemReviewContent.text = data.content
            }
        }
    }
}