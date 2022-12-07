package com.leonardojpr.frytest.network

import android.content.Context
import com.leonardojpr.frytest.BuildConfig
import com.leonardojpr.frytest.utils.Constants.CACHE_SIZE
import com.leonardojpr.frytest.utils.Constants.OK_HTTP_CACHE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesCache(cacheFile: File) : Cache {
        return Cache(cacheFile, CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun providesCacheFile(@ApplicationContext applicationContext: Context): File {
        return File(applicationContext.cacheDir, OK_HTTP_CACHE)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logginInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .readTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .callTimeout(10, TimeUnit.MINUTES)
            .writeTimeout(10, TimeUnit.MINUTES)
            .cache(cache)
            .build()
    }
}