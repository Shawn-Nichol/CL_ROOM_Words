package com.example.cl_room_words.di

import android.content.Context
import com.example.cl_room_words.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}