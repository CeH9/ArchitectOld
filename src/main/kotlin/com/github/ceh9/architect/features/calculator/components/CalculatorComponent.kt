package com.github.ceh9.architect.features.calculator.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.operator.map
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.ceh9.architect.core.utils.mvi.asValue
import com.github.ceh9.architect.core.utils.mvi.getStore
import com.github.ceh9.architect.features.calculator.components.CalculatorStore.Intent
import com.github.ceh9.architect.features.calculator.components.CalculatorStore.State

class CalculatorComponent(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
) : Calculator, ComponentContext by componentContext {

    private val store =
        instanceKeeper.getStore {
            CalculatorStoreProvider(storeFactory = storeFactory).provide()
        }

    override val model: Value<Calculator.Model> = store.asValue().map(stateToModel)

    override fun onIncrementClicked() {
        store.accept(Intent.Increment)
    }

    override fun onDecrementClicked() {
        store.accept(Intent.Decrement)
    }
}

// Mapper
internal val stateToModel: (State) -> Calculator.Model = {
    Calculator.Model(count = it.count)
}