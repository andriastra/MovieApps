package com.example.movieapps.data.source.remote.network

import com.example.movieapps.data.model.response.*
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

    @GET("movie/{id}/videos")
    suspend fun getVideoMovie(
        @Path(value = "id", encoded = false) id: Int,
        @Query("api_key") apiKey: String = API_KEY_MOVIE
    ): VideoResp


    @GET("movie/{id}/reviews")
    suspend fun getMovieReview(
        @Path(value = "id", encoded = false) id: Int,
        @Query("api_key") apiKey: String = API_KEY_MOVIE
    ): MovieReviewResp

}