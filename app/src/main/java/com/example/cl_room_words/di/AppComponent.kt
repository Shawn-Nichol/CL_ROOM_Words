package com.example.cl_room_words.di

import android.content.Context
import com.example.cl_room_words.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)
}