package com.example.movieapps.data.usecase

import com.example.movieapps.data.Resource
import com.example.movieapps.data.model.response.*
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getGenreMovie(): Flow<Resource<GenreMovieResp>>
    fun getDiscoverMovie(page: Int, genre: String): Flow<Resource<MovieResp>>
    fun getMovieDetail(id: Int): Flow<Resource<MovieDetailResp>>
    fun getMovieReview(id: Int): Flow<Resource<MovieReviewResp>>
    fun getVideoMovie(id: Int): Flow<Resource<VideoResp>>
}