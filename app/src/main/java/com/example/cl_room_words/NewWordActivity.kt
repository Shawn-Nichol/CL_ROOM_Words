package com.example.cl_room_words

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.cl_room_words.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText

    private lateinit var binding: ActivityNewWordBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_word)
        binding.binding = this
        editWordView = binding.editWord

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    fun saveWord() {
        val replyIntent = Intent()
        if (TextUtils.isEmpty(editWordView.text)) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val word = editWordView.text.toString()
            replyIntent.putExtra(EXTRA_REPLY, word)
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
    }
}