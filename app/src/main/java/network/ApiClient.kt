package network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance(): ApiService{
        val mHttpLoggingInterceptor = HttpLoggingInterceptor() //menangani logging untuk membantu debug request API
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(mHttpLoggingInterceptor)
            .build()

        val builder = Retrofit.Builder() //mengatur base URL dan konverter JSON
            .baseUrl("https://katanime.vercel.app/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(mOkHttpClient)
            .build()

        return builder.create(ApiService::class.java)

        //ApiClient.getInstance() digunakan di MainActivity.kt untuk membuat panggilan
        //ke API melalui ApiService
    }

}