package com.alexilinskii

import android.app.Application
import art.di.initKoin
import org.koin.android.ext.koin.androidContext

class ArtworkApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin { androidContext(this@ArtworkApplication) }
    }
}