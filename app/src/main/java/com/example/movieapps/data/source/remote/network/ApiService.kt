package com.example.movieapps.data.source.remote.network

import com.example.movieapps.data.model.response.MovieResp
import com.example.movieapps.data.model.response.GenreMovieResp
import com.example.movieapps.data.model.response.MovieDetailResp
import com.example.movieapps.util.Constants.Companion.API_KEY_MOVIE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val MOVIE = "api/v1/"
    }

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("api_key") apiKey: String = API_KEY_MOVIE,
        @Query("page") page: Int,
        @Query("with_genres") genre: String,
    ): MovieResp

    @GET("genre/movie/list")
    suspend fun getGenreMovie(
        @Query("api_key") apiKey: String = API_KEY_MOVIE,
    ): GenreMovieResp

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path(value = "id", encoded = false) id: Int,
        @Query("api_key") apiKey: String = API_KEY_MOVIE
    ): MovieDetailResp
}