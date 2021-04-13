package com.github.ceh9.architect.features.rootFeature

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.RouterState
import com.arkivanov.decompose.router
import com.arkivanov.decompose.statekeeper.Parcelable
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.ceh9.architect.features.calculatorFeature.CalculatorComponent
import com.github.ceh9.architect.features.calculatorFeature.CalculatorComponentImpl
import com.github.ceh9.architect.features.rootFeature.RootComponent.Child

class RootComponentImpl internal constructor(
    componentContext: ComponentContext,
    private val calculator: (ComponentContext) -> CalculatorComponent
) : RootComponent, ComponentContext by componentContext {
    constructor(
        componentContext: ComponentContext,
        storeFactory: StoreFactory,
    ) : this(
        componentContext = componentContext,
        calculator = { childContext ->
            CalculatorComponentImpl(
                componentContext = childContext,
                storeFactory = storeFactory,
            )
        }
    )

    private val router =
        router<Configuration, Child>(
            initialConfiguration = Configuration.Calculator,
            handleBackButton = true,
            childFactory = ::createChild
        )

    override val routerState: Value<RouterState<*, Child>> = router.state

    private fun createChild(configuration: Configuration, componentContext: ComponentContext): Child =
        when (configuration) {
            is Configuration.Calculator -> Child.Calculator(calculator(componentContext))
        }

    private sealed class Configuration : Parcelable {
        object Calculator : Configuration()
    }
}