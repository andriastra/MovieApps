package com.example.movieapps.mvvm.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movieapps.data.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {
    fun getListMovie(page: Int, genre: String) = useCase.getDiscoverMovie(page, genre).asLiveData()
}