package ru.tzkt.etests.network

import android.content.Context
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import ru.tzkt.etests.models.Message

/**
 * Created by user on 10.04.2018.
 */
interface ApiService {

    @POST("api/$apiVersion/tests")
    fun sendTestResults(@Header("Content-Type") contentType: String, @Body message: Message): Observable<Result<Void>>

    companion object Factory {

        private const val apiVersion = "v1"
        private const val HOST_URL = "sdfsdfsdf.ru"


        fun create(context: Context): ApiService {

            val httpClient = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            val client = httpClient.build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(HOST_URL)
                    .client(client)
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}
