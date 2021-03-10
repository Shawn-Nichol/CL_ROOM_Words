package com.example.cl_room_words.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cl_room_words.R
import com.example.cl_room_words.room.Word
import javax.inject.Inject

class WordListAdapter @Inject constructor(): androidx.recyclerview.widget.ListAdapter<Word,
        WordListAdapter.ItemViewHolder>(MyDiffCallback()) {

    class MyDiffCallback : DiffUtil.ItemCallback<Word>() {

        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tv: TextView = view.findViewById(R.id.itemTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.tv.text = item.word
    }
}