package com.example.movieapps.mvvm.movie

import android.os.Bundle
import androidx.activity.viewModels
import com.example.movieapps.R
import com.example.movieapps.base.BaseActivity
import com.example.movieapps.data.Resource
import com.example.movieapps.databinding.ActivityMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : BaseActivity<ActivityMovieBinding>() {
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onLoadObserver() {
        viewModel.getListMovie(1,"").observe(this) { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        println(it)
                    }
                }
                is Resource.Error -> {
                    showToast("")
                }
            }
        }
    }

    override fun onSetupView() {
    }

    override fun onViewClicked() {
    }

    override fun bindToolbarId() = EMPTY_TOOLBAR
    override fun bindLayoutRes() = R.layout.activity_movie


}