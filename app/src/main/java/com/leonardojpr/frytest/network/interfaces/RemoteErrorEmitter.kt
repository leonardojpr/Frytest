package com.leonardojpr.frytest.network.interfaces

import com.leonardojpr.frytest.network.enums.ErrorType

interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
}