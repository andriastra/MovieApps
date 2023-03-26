package com.example.movieapps.mvvm.movie

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapps.R
import com.example.movieapps.base.BaseActivity
import com.example.movieapps.data.Resource
import com.example.movieapps.data.model.response.MovieData
import com.example.movieapps.databinding.ActivityMovieDetailsBinding
import com.example.movieapps.util.Constants
import com.example.movieapps.util.Constants.Companion.BASE_BACKDROP_PATH
import com.example.movieapps.util.Constants.Companion.BASE_URL_IMAGE
import com.example.movieapps.util.Constants.Companion.YOUTUBE_VIDEO_URL
import com.example.movieapps.util.applyToolbarMargin
import com.example.movieapps.util.simpleToolbarWithHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<ActivityMovieDetailsBinding>() {

    private val viewModel: MovieViewModel by viewModels()
    private var dataMovie: MovieData? = null
    private lateinit var reviewAdapter: ReviewListAdapter
    private lateinit var videoAdapter: VideoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let {
            dataMovie = if (SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable("data", MovieData::class.java)
            } else {
                it.getParcelable("data")
            }
            println("data movie = $dataMovie")

        }
        dataMovie = intent.getParcelableExtra("data")
        println("data movie = $dataMovie")

        setupReviewAdapter()
        setupVideoAdapter()
        observeVideoMovie()
        observeReviewMovie()
        onSetupView()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) finish()
        return false
    }

    override fun onLoadObserver() {

    }

    private fun observeVideoMovie() {
        viewBinding.apply {
            dataMovie?.let { data ->
                viewModel.getVideoMovie(data.id).observe(this@MovieDetailActivity) { response ->
                    when (response) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            response.data?.let {
                                if (it.results.size>0) {
                                    detailBody.detailBodyTrailers.visibility = View.VISIBLE
                                    detailBody.detailBodyRecyclerViewTrailers.visibility = View.VISIBLE
                                }
                                videoAdapter.setListData(it.results)
                            }
                        }
                        is Resource.Error -> {
                            showToast("")
                        }
                    }
                }
            }
        }
    }

    private fun observeReviewMovie() {
        viewBinding.apply {
            dataMovie?.let { data ->
                viewModel.getMovieReview(data.id).observe(this@MovieDetailActivity) { response ->
                    when (response) {
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            response.data?.let {
                                if (it.results.size>0) {
                                    detailBody.detailBodyReviews.visibility = View.VISIBLE
                                    detailBody.detailBodyRecyclerViewReviews.visibility = View.VISIBLE
                                }
                                reviewAdapter.setListData(it.results)
                            }
                        }
                        is Resource.Error -> {
                            showToast("")
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun onSetupView() {
        applyToolbarMargin(viewBinding.movieDetailToolbar)
        simpleToolbarWithHome(viewBinding.movieDetailToolbar, "")

        viewBinding.apply {
            println("data movie = $dataMovie")
            dataMovie?.let {
                Glide.with(this@MovieDetailActivity).load("${BASE_BACKDROP_PATH}${it.backdropPath}").into(movieDetailPoster)

                detailHeader.detailHeaderTitle.text = it.title
                detailHeader.detailHeaderRelease.text = "Release Date : ${it.releaseDate}"
                detailHeader.detailHeaderStar.rating = (it.voteAverage/2).toFloat()

                detailBody.detailBodySummary.text = it.overview
            }
        }
    }

    private fun setupVideoAdapter() {
        videoAdapter = VideoAdapter()
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        with(viewBinding.detailBody.detailBodyRecyclerViewTrailers) {
            adapter = videoAdapter
            layoutManager = linearLayoutManager
        }
        videoAdapter.onItemClicked = {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("${YOUTUBE_VIDEO_URL}${it.key}")))
        }
    }

    private fun setupReviewAdapter() {
        reviewAdapter = ReviewListAdapter()
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(viewBinding.detailBody.detailBodyRecyclerViewReviews) {
            adapter = reviewAdapter
            layoutManager = linearLayoutManager
        }
    }



    override fun onViewClicked() {
    }

    override fun bindToolbarId() = EMPTY_TOOLBAR


    override fun bindLayoutRes() = R.layout.activity_movie_details
}