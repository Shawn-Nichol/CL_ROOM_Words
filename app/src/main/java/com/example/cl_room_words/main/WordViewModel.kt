package com.example.cl_room_words.main

import androidx.lifecycle.*
import com.example.cl_room_words.room.Word
import com.example.cl_room_words.room.WordRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }

    fun deleteWord(word: Word) = viewModelScope.launch {
        repository.deleteWord(word)
    }
}

//class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(WordViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return WordViewModel(repository) as T
//
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}