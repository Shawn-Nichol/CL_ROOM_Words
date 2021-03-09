package com.example.cl_room_words

import android.app.Application
import com.example.cl_room_words.di.AppComponent
import com.example.cl_room_words.di.DaggerAppComponent


class WordsApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}