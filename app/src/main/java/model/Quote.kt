package model

data class QuoteResponse(
    val sukses: Boolean,
    val result: List<Quote>
) //menyimpan status sukses dan daftar Quote

data class Quote(
    val id: Int,
    val english: String,
    val indo: String,
    val character: String,
    val anime: String
) //menyimpan data quote, termasuk character, anime, english, info, dan id
//dipakai di QuoteAdapter.kt untuk memetakan setiap quote ke tampilan item
//dan di MainActivity.kt untuk parsing respons API