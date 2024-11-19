package network

import model.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("carikata") // untuk mendefinisikan endpoint untuk mendapatkan data quote
    fun getKata(@Query("kata") kata: String, @Query("page") page: Int): Call<QuoteResponse>
}

