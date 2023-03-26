package com.example.movieapps.mvvm.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movieapps.data.Resource
import com.example.movieapps.data.model.response.MovieResp
import com.example.movieapps.data.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val useCase: MovieUseCase) : ViewModel() {

    private val _page = MutableLiveData<Int>().apply {
        value = 1
    }
    val page: LiveData<Int> = _page

    private val _genre = MutableLiveData<String>().apply {
        value = ""
    }
    val genre: LiveData<String> = _genre

    private val _loadMore = MutableLiveData<Boolean>().apply {
        value = false
    }

    val loadMore: LiveData<Boolean> = _loadMore
    fun setLoadMore(isLoad: Boolean) {
        _loadMore.postValue(isLoad)
    }

    fun setLoadMoreMain(isLoad: Boolean) {
        _loadMore.value = isLoad
    }
    private val _canLoadMore = MutableLiveData<Boolean>().apply {
        value = true
    }

    val canLoadMore: LiveData<Boolean> = _canLoadMore
    fun setCanLoadMore(canLoadMore: Boolean) {
        _canLoadMore.postValue(canLoadMore)
    }

    fun getListMovieByGenre(genre: String): LiveData<Resource<MovieResp>> {
        _page.value = 1
        _genre.value = genre
        return useCase.getDiscoverMovie(page = page.value ?: 1, genre = genre).asLiveData()
    }

    fun loadMoreListMovieByGenre(): LiveData<Resource<MovieResp>> {
        _page.value = (page.value)?.plus(1)
        return useCase.getDiscoverMovie(page = page.value ?: 1, genre = _genre.value?:"").asLiveData()
    }

    fun getListMovie(page: Int, genre: String) = useCase.getDiscoverMovie(page, genre).asLiveData()
    fun getGenresMovie() = useCase.getGenreMovie().asLiveData()
    fun getMovieReview(id: Int) = useCase.getMovieReview(id).asLiveData()
    fun getVideoMovie(id: Int) = useCase.getVideoMovie(id).asLiveData()
}