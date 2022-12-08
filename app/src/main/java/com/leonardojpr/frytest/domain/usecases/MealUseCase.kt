package com.leonardojpr.frytest.domain.usecases

import com.leonardojpr.frytest.domain.api.response.FoodsResponse
import com.leonardojpr.frytest.domain.api.response.MealsItem
import com.leonardojpr.frytest.network.interfaces.RemoteErrorEmitter
import com.leonardojpr.frytest.ui.home.mvvm.MealsRepository
import com.leonardojpr.frytest.ui.home.mvvm.MealsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MealUseCase @Inject constructor(private val repository: MealsRepository){

    private val refreshIntervalMs: Long = 5000
    private val items = mutableListOf<MealsItem>()
    private val limitRequest = 20

    operator fun invoke (emitter: RemoteErrorEmitter) : Flow<MealsViewModel.MealsUIState> = flow {

        while (items.size < limitRequest) {
            val response = repository.getMeals(emitter)
            response?.let {meals ->
                val list = meals.meals
                var find = items.contains(list!!.first())
                if (!find) {
                    items.add(list.first())
                    emit(MealsViewModel.MealsUIState.LoadData(list.first()))
                }
            }
            delay(refreshIntervalMs)
        }

    }
}