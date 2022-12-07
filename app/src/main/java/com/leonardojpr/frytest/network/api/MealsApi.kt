package com.leonardojpr.frytest.network.api

import com.leonardojpr.frytest.domain.api.response.FoodsResponse
import retrofit2.http.GET

interface MealsApi {

    @GET("json/v1/1/random.php")
    suspend fun getMeals() : FoodsResponse
}