package com.example.movieapps.data.source.remote

import com.example.movieapps.data.model.response.MovieResp
import com.example.movieapps.data.model.response.GenreMovieResp
import com.example.movieapps.data.model.response.MovieDetailResp
import com.example.movieapps.data.source.remote.network.ApiResponse
import com.example.movieapps.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getGenreMovie(): Flow<ApiResponse<GenreMovieResp>> {
        return flow {
            try {
                val response =
                    apiService.getGenreMovie()
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Connection Error"))
                Timber.e(e.toString())
            } catch (e: HttpException) {
                emit(ApiResponse.Error("Error connecting to database"))
                Timber.e(e.toString())
            } catch (e: Exception) {
                emit(ApiResponse.Error("An Error Occurred"))
                Timber.e(e.stackTraceToString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDiscoverMovie(
        page: Int,
        genre: String
    ): Flow<ApiResponse<MovieResp>> {
        return flow {
            try {
                val response =
                    apiService.getDiscoverMovie(page = page,genre = genre)
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Connection Error"))
                Timber.e(e.toString())
            } catch (e: HttpException) {
                emit(ApiResponse.Error("Error connecting to database"))
                Timber.e(e.toString())
            } catch (e: Exception) {
                emit(ApiResponse.Error("An Error Occurred"))
                Timber.e(e.stackTraceToString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieDetail(
        id: Int
    ): Flow<ApiResponse<MovieDetailResp>> {
        return flow {
            try {
                val response =
                    apiService.getMovieDetail(id = id)
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Connection Error"))
                Timber.e(e.toString())
            } catch (e: HttpException) {
                emit(ApiResponse.Error("Error connecting to database"))
                Timber.e(e.toString())
            } catch (e: Exception) {
                emit(ApiResponse.Error("An Error Occurred"))
                Timber.e(e.stackTraceToString())
            }
        }.flowOn(Dispatchers.IO)
    }
}