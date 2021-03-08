package com.example.cl_room_words

import android.app.Application
import com.example.cl_room_words.di.AppComponent
import com.example.cl_room_words.di.DaggerAppComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Use lazy so the database and the repository are only created when they're needed rather than
    // when the application starts.
//    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {WordRepository(database.wordDao())}


    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}