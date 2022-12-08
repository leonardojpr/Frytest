package com.leonardojpr.frytest.ui.home.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardojpr.frytest.domain.api.response.MealsItem
import com.leonardojpr.frytest.domain.usecases.MealUseCase
import com.leonardojpr.frytest.network.enums.ErrorType
import com.leonardojpr.frytest.network.interfaces.RemoteErrorEmitter
import com.leonardojpr.frytest.ui.home.adapter.MealsAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val mealUseCase: MealUseCase) : ViewModel(),
    RemoteErrorEmitter {

    private val _uiState = MutableStateFlow(MealsUIState.LoadData())
    val uiState: StateFlow<MealsUIState> = _uiState

    var adapter = MealsAdapter()

    fun getMeals() {
        mealUseCase(emitter = this@MealsViewModel)
            .onEach {
                when (it) {
                    is MealsUIState.LoadData -> {
                        adapter.addMeals(it.item!!)
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
    }

    override fun onError(msg: String) {

    }

    override fun onError(errorType: ErrorType) {

    }

    sealed class MealsUIState() {
        class Loading(val isLoading: Boolean) : MealsUIState()
        class LoadData(val item: MealsItem? = null) : MealsUIState()
    }
}