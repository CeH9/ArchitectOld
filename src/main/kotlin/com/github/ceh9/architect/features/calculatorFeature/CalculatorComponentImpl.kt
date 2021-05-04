package com.github.ceh9.architect.features.calculatorFeature

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.ceh9.architect.core.utils.mvi.getStore
import com.github.ceh9.architect.core.utils.mvi.statesAsStateFlow
import com.github.ceh9.architect.features.calculatorFeature.CalculatorStore.Intent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class CalculatorComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : CalculatorComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        CalculatorStoreProvider(storeFactory = storeFactory).provide()
    }

    override val model = store.statesAsStateFlow()

    override fun onIncrementClicked() {
        store.accept(Intent.Increment)
    }

    override fun onDecrementClicked() {
        store.accept(Intent.Decrement)
    }
}