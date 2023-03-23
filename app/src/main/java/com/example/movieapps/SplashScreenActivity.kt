package com.example.movieapps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapps.mvvm.movie.MovieActivity
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: AppCompatActivity() {

    private val mDelayJob: CompletableJob = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splash()
    }

    private fun splash(){
        CoroutineScope(Dispatchers.Main).launch(mDelayJob){
            delay(2000)
            finish()
            startActivity(Intent(this@SplashScreenActivity, MovieActivity::class.java))
        }
    }
}