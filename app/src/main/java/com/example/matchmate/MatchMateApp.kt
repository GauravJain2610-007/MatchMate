package com.example.matchmate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MatchMateApp : Application(){

    override fun onCreate() {
        super.onCreate()

    }
}