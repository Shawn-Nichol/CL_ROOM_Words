package com.example.cl_room_words

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class WordViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
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