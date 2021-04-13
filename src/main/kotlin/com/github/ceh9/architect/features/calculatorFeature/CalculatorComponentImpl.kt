package com.github.ceh9.architect.features.calculatorFeature

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.ceh9.architect.core.utils.mvi.asValue
import com.github.ceh9.architect.core.utils.mvi.getStore
import com.github.ceh9.architect.features.calculatorFeature.CalculatorStore.Intent
import com.github.ceh9.architect.features.calculatorFeature.CalculatorStore.State

class CalculatorComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : CalculatorComponent, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            CalculatorStoreProvider(storeFactory = storeFactory).provide()
        }

    override val model: Value<CalculatorComponent.Model> = store.asValue().map(stateToModel)

    override fun onIncrementClicked() {
        store.accept(Intent.Increment)
    }

    override fun onDecrementClicked() {
        store.accept(Intent.Decrement)
    }
}

// Mapper
internal val stateToModel: (State) -> CalculatorComponent.Model = {
    CalculatorComponent.Model(count = it.count)
}