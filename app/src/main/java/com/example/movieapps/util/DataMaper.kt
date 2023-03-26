package com.example.movieapps.util

import com.example.movieapps.data.model.response.GenreMovieResp

object DataMaper {
    fun mapListGenre(genreMovieResp: GenreMovieResp?) : MutableList<String> {
        val genreList = mutableListOf<String>()
        genreMovieResp?.genres?.map {
            genreList.add(it.name)
        }

        return genreList
    }
    fun mapGenre(genreMovieResp: GenreMovieResp?) : String {
        var genre = ""
        genreMovieResp?.genres?.map {
            genre = if (genre.isEmpty()) {
                it.name
            } else {
                "$genre, ${it.name}"
            }
        }

        return genre
    }

    fun mapGetIdGenre(genreMovieResp: GenreMovieResp?, genreName : String) : String {
        var genreId = ""
        genreMovieResp?.genres?.map {
            if (it.name.equals(genreName,true)) {
                genreId =  it.id.toString()
            }
        }
        return genreId
    }
}