package com.example.movieapps.data.usecase

import com.example.movieapps.data.Resource
import com.example.movieapps.data.model.response.GenreMovieResp
import com.example.movieapps.data.model.response.MovieDetailResp
import com.example.movieapps.data.model.response.MovieResp
import com.example.movieapps.data.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getGenreMovie(): Flow<Resource<GenreMovieResp>> =
        movieRepository.getGenreMovie()

    override fun getDiscoverMovie(page: Int, genre: String): Flow<Resource<MovieResp>> =
        movieRepository.getDiscoverMovie(page,genre)

    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetailResp>> =
        movieRepository.getMovieDetail(id)
}