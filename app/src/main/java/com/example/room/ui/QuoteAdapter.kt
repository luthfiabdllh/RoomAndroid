package com.example.room.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.R
import com.example.room.databinding.ItemQuoteBinding
import model.Quote

class QuoteAdapter(
    private val onBookmarkClick: (Quote, Boolean) -> Unit
) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    private var quotes: List<Quote> = listOf() // Menyimpan daftar quotes yang akan ditampilkan
    private var bookmarkedQuotes: Set<Int> = emptySet() // Menyimpan set ID quotes yang sudah dibookmark

    fun setQuotes(quotes: List<Quote>, bookmarked: Set<Int>) {
        this.quotes = quotes
        this.bookmarkedQuotes = bookmarked
        notifyDataSetChanged()
    } //mengupdate daftar quote di RecyclerView

    // Fungsi untuk membuat ViewHolder baru untuk setiap item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding) // Mengembalikan ViewHolder baru yang berisi item view
    }

    // Fungsi untuk mengikat data (quote) ke ViewHolder dan mengatur status bookmark
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = quotes[position]
        val isBookmarked = bookmarkedQuotes.contains(quote.id)
        holder.bind(quote, isBookmarked)
    }

    override fun getItemCount() = quotes.size

    inner class QuoteViewHolder(private val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote, isBookmarked: Boolean) {
            binding.tvCharacter.text = "Character: ${quote.character}"
            binding.tvAnime.text = "Anime: ${quote.anime}"
            binding.tvEnglish.text = "English: ${quote.english}"
            binding.tvIndo.text = "Indo: ${quote.indo}"

            binding.ivBookmark.setImageResource(
                if (isBookmarked) R.drawable.baseline_bookmark_24 else R.drawable.baseline_bookmark_border_24
            )

            binding.ivBookmark.setOnClickListener {
                val newState = !isBookmarked
                onBookmarkClick(quote, newState)
            }
        }
    } //inner class untuk menampilkan setiap quote
    //bind untuk menampilkan teks seperti character, anime, dll di setiap textview
}
