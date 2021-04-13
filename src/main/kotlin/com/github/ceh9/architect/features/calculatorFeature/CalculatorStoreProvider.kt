package com.github.ceh9.architect.features.calculatorFeature

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.SuspendExecutor
import com.github.ceh9.architect.features.calculatorFeature.CalculatorStore.*

class CalculatorStoreProvider(
    private val storeFactory: StoreFactory,
) {
    fun provide(): CalculatorStore = object : CalculatorStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "CalculatorStore",
            initialState = State(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed class Result {
        data class CountChanged(val value: Int) : Result()
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(result: Result): State =
            when (result) {
                is Result.CountChanged -> copy(count = result.value)
            }
    }

    private inner class ExecutorImpl : SuspendExecutor<Intent, Unit, State, Result, Label>() {

        override suspend fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.Decrement -> {
                    dispatch(Result.CountChanged(getState().count - 1))
                }
                is Intent.Increment -> {
                    dispatch(Result.CountChanged(getState().count + 1))
                }
            }
        }
    }
}