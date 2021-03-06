package com.example.cl_room_words.di

import android.content.Context
import com.example.cl_room_words.room.WordDao
import com.example.cl_room_words.room.WordRoomDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class RoomModule {

    companion object {
        val applicationScope = CoroutineScope(SupervisorJob())

        @Provides
        @Singleton
        fun provideDatabase(context: Context): WordRoomDatabase {
            return WordRoomDatabase.getDatabase(context, applicationScope)
        }

        @Provides
        @Singleton
        fun provideWordDao(database: WordRoomDatabase): WordDao {
            return database.wordDao()
        }
    }
}