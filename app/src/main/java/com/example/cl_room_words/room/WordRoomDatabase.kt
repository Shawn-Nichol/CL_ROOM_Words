package com.example.cl_room_words.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @Database: declare entities that belong in the database and set the version number. Each entity
 * corresponds to a table and will be created in the data base.
 *
 * Class for Room must be abstract and extend RoomDatabase.
 *
 * The Database exposes the DAOs through an abstract getter method for each DAO
 *
 */
@Database(entities = arrayOf(Word::class), version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        // Singleton prevents multiple instance of database opening at the same time.
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        // returns the singleton. It'll create the database the first time it's accessed, using Room's
        // database builder to create a RoomDatabase object in the application context from the
        // WordRoomDatabase class and names it.
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): WordRoomDatabase {
            // If the INSTANCE is not null, then return it,
            // If it is, then create the database.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class WordDatabaseCallback(private val scope: CoroutineScope) :  RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.wordDao())
                }
            }
        }

        suspend fun populateDatabase(wordDao: WordDao) {
            // Delete all content here.
            wordDao.deleteAll()

            // Add sample words.
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }
    }
}

