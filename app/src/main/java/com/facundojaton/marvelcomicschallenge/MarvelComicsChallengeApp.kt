package com.facundojaton.marvelcomicschallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelComicsChallengeApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}