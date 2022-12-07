package com.leonardojpr.frytest.ui.home

import com.leonardojpr.frytest.network.api.MealsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MealsModule {

    @Provides
    @Singleton
    fun provideshomeApi(retrofit: Retrofit): MealsApi = retrofit.create(MealsApi::class.java)

}