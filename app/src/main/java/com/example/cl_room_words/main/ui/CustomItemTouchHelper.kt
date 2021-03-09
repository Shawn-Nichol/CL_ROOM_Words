package com.example.cl_room_words.main.ui

import android.content.Context
import android.graphics.Color
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.cl_room_words.main.WordViewModel
import com.example.cl_room_words.room.Word

class CustomItemTouchHelper(mContext: Context, val viewModel: WordViewModel) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.LEFT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val startPosition = viewHolder.adapterPosition
        val endPosition = target.adapterPosition
        recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val editList: MutableList<Word>? = viewModel.allWords.value as MutableList<Word>?
        val editWordList = editList?.toMutableList()

        val word: Word = editWordList!![viewHolder.adapterPosition]
        viewModel.deleteWord(word)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            viewHolder?.itemView?.setBackgroundColor(Color.RED)

        }
    }
}