package com.example.movieapps.data.model.response
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResp(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieData>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
) : Parcelable

@Parcelize
data class MovieData(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf(),
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
): Parcelable
