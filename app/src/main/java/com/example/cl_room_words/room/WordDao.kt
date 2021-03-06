package com.example.cl_room_words.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


/**
 * Dao is an interface: DAO must either be an interface or abstract class.
 * @DAO: identifies it as a DAO class for Room
 */
@Dao
interface WordDao {
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    // @Insert: is special DAO method annotation where you don't have to provide any SQL.
    // onConflictStrategy: ignores matching words.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(word: Word)
}