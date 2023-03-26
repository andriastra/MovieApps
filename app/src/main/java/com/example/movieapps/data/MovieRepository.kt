package com.example.movieapps.data

import com.example.movieapps.data.model.response.*
import com.example.movieapps.data.repository.IMovieRepository
import com.example.movieapps.data.source.remote.RemoteDataSource
import com.example.movieapps.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {

    override fun getGenreMovie(): Flow<Resource<GenreMovieResp>> =
        object : NetworkOnlyResource<GenreMovieResp>() {
            override suspend fun createCall(): Flow<ApiResponse<GenreMovieResp>> =
                remoteDataSource.getGenreMovie()

            override suspend fun saveCallResult(data: GenreMovieResp) {}

        }.asFlow()

    override fun getDiscoverMovie(page: Int, genre: String): Flow<Resource<MovieResp>> =
        object : NetworkOnlyResource<MovieResp>() {
            override suspend fun createCall(): Flow<ApiResponse<MovieResp>> =
                remoteDataSource.getDiscoverMovie(page,genre)

            override suspend fun saveCallResult(data: MovieResp) {}

        }.asFlow()

    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetailResp>> =
        object : NetworkOnlyResource<MovieDetailResp>() {
            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResp>> =
                remoteDataSource.getMovieDetail(id)

            override suspend fun saveCallResult(data: MovieDetailResp) {}

        }.asFlow()

    override fun getVideoMovie(id: Int): Flow<Resource<VideoResp>> =
        object : NetworkOnlyResource<VideoResp>() {
            override suspend fun createCall(): Flow<ApiResponse<VideoResp>> =
                remoteDataSource.getVideoMovie(id)

            override suspend fun saveCallResult(data: VideoResp) {}

        }.asFlow()

    override fun getMovieReview(id: Int): Flow<Resource<MovieReviewResp>> =
        object : NetworkOnlyResource<MovieReviewResp>() {
            override suspend fun createCall(): Flow<ApiResponse<MovieReviewResp>> =
                remoteDataSource.getMovieReview(id)

            override suspend fun saveCallResult(data: MovieReviewResp) {}

        }.asFlow()
}