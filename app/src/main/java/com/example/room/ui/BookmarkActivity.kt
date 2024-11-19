package com.example.room.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.database.BookmarkRoomDatabase
import com.example.room.databinding.ActivityBookmarkBinding
import model.Quote

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    private val bookmarkDao by lazy {
        BookmarkRoomDatabase.getDatabase(this)?.bookmarkDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        bookmarkDao?.allBookmark?.observe(this) { bookmarks ->
            val adapter = QuoteAdapter { _, _ -> }
            adapter.setQuotes(bookmarks.map {
                Quote(it.id, it.english, it.indo, it.character, it.anime)
            }, bookmarks.map { it.id }.toSet())

            binding.rvBookmarks.adapter = adapter
        }
    }

    private fun setupRecyclerView() {
        binding.rvBookmarks.layoutManager = LinearLayoutManager(this)
    }
}
