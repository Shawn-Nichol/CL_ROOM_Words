package com.example.cl_room_words.room

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Pass in the DAO instead of the whole database, because you only need to access the DAO.
 */
@Singleton
class WordRepository @Inject constructor(private val wordDao: WordDao) {
    // Room Executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to implement
    // anything else to ensure we're not doing long running database work off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }

    suspend fun deleteWord(word: Word) {
        wordDao.delete(word)
    }
}