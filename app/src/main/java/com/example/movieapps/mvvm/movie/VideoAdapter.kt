package com.example.movieapps.mvvm.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.base.BaseRecyclerAdapter
import com.example.movieapps.data.model.response.VideoData
import com.example.movieapps.databinding.ItemVideoBinding
import com.example.movieapps.util.Constants.Companion.YOUTUBE_THUMBNAIL_URL

class VideoAdapter: BaseRecyclerAdapter<VideoData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_video, parent, false)
        )

    inner class ViewHolder(itemView: View) : BaseViewHolder<VideoData>(itemView) {

        private val binding = ItemVideoBinding.bind(itemView)

        init {
            binding.itemVideoCover.setOnClickListener {
                onItemClicked?.invoke(masterList[bindingAdapterPosition])
            }
        }

        override fun bind(data: VideoData) {
            with(binding) {
                itemVideoTitle.text = data.name
                Glide.with(itemView.context)
                    .load("${YOUTUBE_THUMBNAIL_URL}${data.key}/default.jpg")
                    .into(itemVideoCover)
            }
        }
    }
}