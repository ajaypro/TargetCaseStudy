package com.target.targetcasestudy.data.network

import com.target.targetcasestudy.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * Creating retrofit instance and networkservice instance, calladapterfactory not used as we are using coroutines with retrofit
 * as suspend function wraps the response from network.
 */
object Networking {

        private const val NETWORK_CALL_TIMEOUT = 60

        fun create(baseUrl: String, cacheDir: File, cacheSize: Long): NetworkService {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(
                    OkHttpClient.Builder()
                        .cache(Cache(cacheDir, cacheSize))
                        .addInterceptor(
                            HttpLoggingInterceptor()
                                .apply {
                                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                                    else HttpLoggingInterceptor.Level.NONE
                                })
                        .readTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .writeTimeout(NETWORK_CALL_TIMEOUT.toLong(), TimeUnit.SECONDS)
                        .build()
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetworkService::class.java)
        }
    }
