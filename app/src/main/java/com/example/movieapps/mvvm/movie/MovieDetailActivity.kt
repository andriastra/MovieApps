package com.example.movieapps.mvvm.movie

import android.os.Bundle
import com.example.movieapps.R
import com.example.movieapps.base.BaseActivity
import com.example.movieapps.databinding.ActivityMovieDetailsBinding

class MovieDetailActivity : BaseActivity<ActivityMovieDetailsBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onLoadObserver() {
    }

    override fun onSetupView() {
    }

    override fun onViewClicked() {
    }

    override fun bindToolbarId() = EMPTY_TOOLBAR


    override fun bindLayoutRes() = R.layout.activity_movie_details
}