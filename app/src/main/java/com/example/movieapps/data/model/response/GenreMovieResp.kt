package com.example.movieapps.data.model.response

data class GenreMovieResp(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}

