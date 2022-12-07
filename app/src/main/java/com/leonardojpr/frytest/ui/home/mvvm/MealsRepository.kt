package com.leonardojpr.frytest.ui.home.mvvm

import com.leonardojpr.frytest.network.api.MealsApi
import com.leonardojpr.frytest.network.interfaces.RemoteErrorEmitter
import com.leonardojpr.frytest.utils.BaseRemoteRepository
import javax.inject.Inject

class MealsRepository @Inject constructor(private val mealsApi: MealsApi) : BaseRemoteRepository() {

    suspend fun getMeals(emitter: RemoteErrorEmitter) = safeApiCall(emitter) {
        mealsApi.getMeals()
    }

}