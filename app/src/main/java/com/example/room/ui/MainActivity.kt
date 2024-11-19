package com.example.room.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.database.Bookmark
import com.example.room.database.BookmarkRoomDatabase
import com.example.room.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Quote
import model.QuoteResponse
import network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var quoteAdapter: QuoteAdapter
    private val bookmarkDao by lazy {
        BookmarkRoomDatabase.getDatabase(this)?.bookmarkDao()
    }

    private var quotesList: List<Quote> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        // Fetch quotes dan bookmarks
        fetchQuotes()

        binding.btnBookmark.setOnClickListener {
            val intent = Intent(this, BookmarkActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        quoteAdapter = QuoteAdapter { quote, isBookmarked ->
            handleBookmarkAction(quote, isBookmarked)
        }

        binding.rvQuote.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = quoteAdapter
        }
    }

    private fun fetchQuotes() {
        val apiService = ApiClient.getInstance()
        val call = apiService.getKata("tidak akan", 1)

        call.enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful) {
                    response.body()?.result?.let { quotes ->
                        quotesList = quotes

                        quoteAdapter.setQuotes(quotes, emptySet())

                        fetchBookmarks()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load quotes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchBookmarks() {
        bookmarkDao?.allBookmark?.observe(this) { bookmarks ->
            val bookmarkedIds = bookmarks.map { it.id }.toSet()
            quoteAdapter.setQuotes(quotesList, bookmarkedIds)
        }
    }

    private fun handleBookmarkAction(quote: Quote, isBookmarked: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            if (isBookmarked) {
                bookmarkDao?.insert(
                    Bookmark(
                        character = quote.character,
                        anime = quote.anime,
                        english = quote.english,
                        indo = quote.indo
                    )
                )
            } else {
                bookmarkDao?.delete(
                    Bookmark(
                        character = quote.character,
                        anime = quote.anime,
                        english = quote.english,
                        indo = quote.indo
                    )
                )
            }
        }
    }
}
