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
)