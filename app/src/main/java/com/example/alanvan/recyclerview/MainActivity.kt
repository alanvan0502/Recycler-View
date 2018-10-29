package com.example.alanvan.recyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val mWordList: LinkedList<String> = LinkedList()
    private val mWordListOriginal: LinkedList<String> = LinkedList()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: WordListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val wordListSize = mWordList.size
            // Add a new word to the wordList
            mWordList.addLast("Word $wordListSize")
            // Notify the adapter, that the data has changed
            mRecyclerView.adapter!!.notifyDataSetChanged()
            // Scroll to the bottom
            mRecyclerView.smoothScrollToPosition(wordListSize)
        }

        // Put initial data into the word list
        for (i in 0..20) {
            mWordList.addLast("Word $i")
            mWordListOriginal.addLast("Word $i")
        }

        // Get a handle to the RecyclerView
        mRecyclerView = findViewById(R.id.recyclerview)
        // Create an adapter and supply the data to be displayed
        mAdapter = WordListAdapter(this, mWordList)
        // Connect the adapter with the RecyclerView
        mRecyclerView.adapter = mAdapter
        // Give the RecyclerView a default layout manager
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_reset -> {
                resetToOriginalList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Reset the mWordList to the original list & notify the adapter that the data has changed
    private fun resetToOriginalList() {
        val listSize = mWordList.size

        for (i in 0 until listSize){
            mWordList.removeAt(0)
        }

        for (s in mWordListOriginal) {
            mWordList.addLast(s)
        }

        mRecyclerView.adapter!!.notifyDataSetChanged()
    }
}
