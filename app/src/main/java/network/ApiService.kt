package network

import model.QuoteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("carikata") // untuk mendefinisikan endpoint untuk mendapatkan data quote
    fun getKata(@Query("kata") kata: String, @Query("page") page: Int): Call<QuoteResponse>
    //fungsi untuk mengambil parameter kata (string) dan page (int) untuk melakukan
    //pencarian berdasarkan kata tertentu
    //dipanggil di ApiClient.kt untuk membuat instance dari ApiService, dan di MainActivity.kt
    //untuk memanggil data API
}

