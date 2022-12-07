package com.leonardojpr.frytest.utils

import android.util.Log
import com.leonardojpr.frytest.network.enums.ErrorType
import com.leonardojpr.frytest.network.interfaces.RemoteErrorEmitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

open class BaseRemoteRepository {

    @Inject
    lateinit var retrofit: Retrofit

    companion object {
        val TAG = BaseRemoteRepository::class.java.simpleName
        const val MESSAGE_KEY = "message"
    }

    suspend inline fun <T> safeApiCall(
        emitter: RemoteErrorEmitter,
        crossinline callFunction: suspend () -> T,
    ): T? {
        return try {
            val myObject = withContext(Dispatchers.IO) { callFunction.invoke() }
            myObject
        } catch (e: Exception) {
            getError(e, emitter)
            null
        }
    }

    suspend fun getError(e: Exception, emitter: RemoteErrorEmitter) {
        withContext(Dispatchers.Main) {
            Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
            when (e) {
                is HttpException -> {
                    if (e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
                    if (e.code() in 500..599) emitter.onError(ErrorType.UNKNOWN)
                    val body = e.response()
                    emitter.onError(ErrorType.SESSION_EXPIRED)

                }
                is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
                is IOException -> emitter.onError(ErrorType.NETWORK)
                else -> emitter.onError(ErrorType.UNKNOWN)
            }
        }
    }


}