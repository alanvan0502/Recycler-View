package com.example.alanvan.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class WordListAdapter (context: Context, wordList: LinkedList<String>)
    : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val mWordList = wordList
    private val mInflater = LayoutInflater.from(context)

    // Creates the view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListAdapter.WordViewHolder {
        val mItemView = mInflater.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(mItemView, this)
    }

    // Gets the number of items in data
    override fun getItemCount(): Int {
        return mWordList.size
    }

    // Connects the data to the view holder
    override fun onBindViewHolder(holder: WordListAdapter.WordViewHolder, position: Int) {
        val mCurrent = mWordList[position]
        holder.wordItemView.text = mCurrent
    }

    inner class WordViewHolder(itemView: View, adapter: WordListAdapter)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val wordItemView: TextView = itemView.findViewById(R.id.word)
        private val mAdapter: WordListAdapter = adapter

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            // Get the position of the item that was clicked
            val mPosition = layoutPosition
            // Access the affected item in the mWordList
            val element = mWordList[mPosition]
            // Change the word in mWordList
            mWordList[mPosition] = "Clicked! $element"
            // Notify the adapter, that the data ahas changed so that it can update the RecyclerView to display
            // the data
            mAdapter.notifyDataSetChanged()
        }
    }
}