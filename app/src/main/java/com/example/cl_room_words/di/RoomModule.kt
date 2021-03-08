package com.example.cl_room_words.di

import android.content.Context
import androidx.room.Room
import com.example.cl_room_words.WordDao
import com.example.cl_room_words.WordRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(context: Context): WordRoomDatabase {
            return Room.databaseBuilder(context, WordRoomDatabase::class.java, "db").build()
        }

        @Provides
        @Singleton
        fun provideWordDao(database: WordRoomDatabase): WordDao {
            return database.wordDao()
        }
    }
}