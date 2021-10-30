package com.facundojaton.marvelcomicschallenge

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MarvelComicsChallengeApp: Application() {

    override fun onCreate() {
        FacebookSdk.sdkInitialize(applicationContext);
        AppEventsLogger.activateApp(this);
        super.onCreate()
    }
}