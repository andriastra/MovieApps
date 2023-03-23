package com.example.movieapps

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApp : Application() {

    private var instance: MovieApp? = null
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}