package com.example.movieapps.mvvm.movie

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.R
import com.example.movieapps.base.BaseActivity
import com.example.movieapps.data.Resource
import com.example.movieapps.data.model.response.GenreMovieResp
import com.example.movieapps.databinding.ActivityMovieBinding
import com.example.movieapps.util.DataMaper.mapGenre
import com.example.movieapps.util.DataMaper.mapGetIdGenre
import com.example.movieapps.util.DataMaper.mapListGenre
import com.example.movieapps.util.addNewFilterChips
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : BaseActivity<ActivityMovieBinding>() {
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieByGenreAdapter: MovieByGenreAdapter
    private lateinit var dataGenre: GenreMovieResp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetupView()
        setupMovieByGenreAdapter()
        getObserveDiscoverMovie("")
    }

    override fun onLoadObserver() {
        viewModel.getGenresMovie().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    response.data?.let {
                        dataGenre = it
                        viewBinding.cgGenre.removeAllViews()
                        println(mapGenre(it))
                        val listGenre = mapListGenre(it)
                        listGenre.forEach {
                            viewBinding.cgGenre.addNewFilterChips(
                                it,
                                layoutInflater,
                                false
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    showToast("")
                }
            }
        }
    }

    fun onSetupView() {
        viewBinding.cgGenre.setOnCheckedChangeListener { group, checkedId ->
            println("cg selected = ${group.findViewById<Chip>(checkedId)}")
            if (group.findViewById<Chip>(checkedId) != null) {
                viewBinding.cgGenre.checkedChipIds.forEach { chipsId ->
                    val chip = viewBinding.root.findViewById<Chip>(chipsId)
                    val genreName = chip?.text.toString()
                    getObserveDiscoverMovie(mapGetIdGenre(dataGenre, genreName))
                }
            } else {
                getObserveDiscoverMovie("")
            }
        }

        viewBinding.rvDiscoverMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (viewModel.canLoadMore.value == true && viewModel.loadMore.value == false) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == 19) {

                        //bottom of list!
                        loadMoreMovie()
                    }
                }
            }
        })
    }

    override fun onViewClicked() {
    }

    private fun getObserveDiscoverMovie(genre: String) {
        viewModel.getListMovieByGenre(genre).observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    viewModel.setLoadMore(true)
                    toogleLoading()
                }
                is Resource.Success -> {
                    response.data?.let {
                        if(it.page == it.totalPages ) viewModel.setCanLoadMore(false)
                        movieByGenreAdapter.setListData(it.results)
                        viewModel.setLoadMore(false)
                        toogleLoading()
                    }
                }
                is Resource.Error -> {
                    viewModel.setCanLoadMore(false)
                    viewModel.setLoadMore(false)
                    showToast("Sorry we have truble now, try again latter..")
                    toogleLoading()
                }
            }
        }
    }


    private fun loadMoreMovie() {
        viewModel.loadMoreListMovieByGenre().observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    toogleLoading()
                }
                is Resource.Success -> {
                    response.data?.let {
                        if(it.page == it.totalPages ) viewModel.setCanLoadMore(false)
                        movieByGenreAdapter.loadMoreData(it.results)
                        toogleLoading()
                    }
                }
                is Resource.Error -> {
                    viewModel.setCanLoadMore(false)
                    showToast("Sorry we have truble now, try again latter..")
                    toogleLoading()
                }
            }
        }
    }

    private fun setupMovieByGenreAdapter() {
        movieByGenreAdapter = MovieByGenreAdapter()
        val layoutManager = GridLayoutManager(
            this,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        with(viewBinding.rvDiscoverMovie) {
            adapter = movieByGenreAdapter
            this.layoutManager = layoutManager
        }

        movieByGenreAdapter.onItemClicked = {
            println("parsing data movie = $it")
            val intent = Intent(this, MovieDetailActivity::class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
    }

    override fun bindToolbarId() = EMPTY_TOOLBAR
    override fun bindLayoutRes() = R.layout.activity_movie

    private fun toogleLoading() {
        if (viewModel.page.value == 1) {
            if (viewBinding.defaultProgress.isShown) {
                viewBinding.defaultProgress.visibility = View.GONE
            } else {
                viewBinding.defaultProgress.visibility = View.VISIBLE
            }
        } else {
            if (viewBinding.loadMoreProgress.isShown) {
                viewBinding.loadMoreProgress.visibility = View.GONE
            } else {
                viewBinding.loadMoreProgress.visibility = View.VISIBLE
            }
        }
    }

}