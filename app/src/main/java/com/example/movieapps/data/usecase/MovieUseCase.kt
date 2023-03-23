package com.example.movieapps.data.usecase

import com.example.movieapps.data.Resource
import com.example.movieapps.data.model.response.GenreMovieResp
import com.example.movieapps.data.model.response.MovieDetailResp
import com.example.movieapps.data.model.response.MovieResp
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getGenreMovie(): Flow<Resource<GenreMovieResp>>
    fun getDiscoverMovie(page: Int, genre: String): Flow<Resource<MovieResp>>
    fun getMovieDetail(id: Int): Flow<Resource<MovieDetailResp>>
}