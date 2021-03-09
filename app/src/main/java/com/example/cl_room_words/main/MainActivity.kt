package com.example.cl_room_words.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cl_room_words.*
import com.example.cl_room_words.databinding.ActivityMainBinding
import com.example.cl_room_words.room.Word
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1

    private lateinit var binding: ActivityMainBinding
    private val rvAdapter = WordListAdapter()

    @Inject
    lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WordsApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.binding = this

        initRecyclerView()
        submitList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(reply)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                    applicationContext,
                R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun initRecyclerView() {
        val recyclerView = binding.recyclerview

        recyclerView.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    /**
     * Observer the getAlphabetizedWords, the OnChanged() method fires when the observed data changes.
     */
    private fun submitList() {

        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { rvAdapter.submitList(it) }
        }
    }

    fun fabClick() {
        val intent = Intent(this@MainActivity, NewWordActivity::class.java)
        startActivityForResult(intent, newWordActivityRequestCode)
    }
}

