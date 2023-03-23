package com.example.movieapps.di

import com.example.movieapps.data.MovieRepository
import com.example.movieapps.data.usecase.MovieInteractor
import com.example.movieapps.data.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

}