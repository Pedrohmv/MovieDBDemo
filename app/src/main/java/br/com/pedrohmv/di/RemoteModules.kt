package br.com.pedrohmv.di

import android.content.Context
import br.com.pedrohmv.data.FilmeRemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModules = module {

    single { createOkHttpClient() }

    single { createWebService<FilmeRemoteDataSource>(get()) }

}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addNetworkInterceptor { chain ->
            val request = chain.request()
            val url = request.url().newBuilder()
                .addQueryParameter("api_key", API_KEY)
//                .addQueryParameter("language", "pt-BR")
                .build()
            val newRequest = request.newBuilder().url(url).build()
            chain.proceed(newRequest)
        }
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

const val API_KEY = "2b2ecc513d43ca197bb41969dd605be0"
const val API_URL = "https://api.themoviedb.org/3/"
const val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
const val THUMB_URL = "https://img.youtube.com/vi/"