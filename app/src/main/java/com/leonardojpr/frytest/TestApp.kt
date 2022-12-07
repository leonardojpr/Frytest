package com.leonardojpr.frytest

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestApp : Application()  {

    companion object {
        lateinit var appInstance: TestApp
         val appContext: Context by lazy {
             appInstance.applicationContext
         }
    }

    override fun onCreate() {
        super.onCreate()
    }

}