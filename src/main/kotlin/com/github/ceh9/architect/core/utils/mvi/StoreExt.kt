package com.github.ceh9.architect.core.utils.mvi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.lifecycle.DefaultLifecycleCallbacks
import com.arkivanov.decompose.lifecycle.Lifecycle
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.ValueObserver
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.rx.Disposable
import com.arkivanov.mvikotlin.rx.observer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun <T : Any> Store<*, T, *>.asValue(): Value<T> =
    object : Value<T>() {
        override val value: T get() = state
        private var disposables = emptyMap<ValueObserver<T>, Disposable>()

        override fun subscribe(observer: ValueObserver<T>) {
            val disposable = states(com.arkivanov.mvikotlin.rx.observer(onNext = observer))
            this.disposables += observer to disposable
        }

        override fun unsubscribe(observer: ValueObserver<T>) {
            val disposable = disposables[observer] ?: return
            this.disposables -= observer
            disposable.dispose()
        }
    }

fun <State : Any> Store<*, State, *>.statesAsStateFlow(): StateFlow<State> {
    val stateFlow = MutableStateFlow(state)

    states(observer(
        onComplete = { },
        onNext = { stateFlow.value = it }
    ))

    return stateFlow
}

fun <T : Any> Store<*, T, *>.asState(lifecycle: Lifecycle): State<T> {
    val mutableState = mutableStateOf(state)

    lifecycle.subscribe(
        object : DefaultLifecycleCallbacks {
            private var disposable: Disposable? = null

            override fun onCreate() {
                disposable =
                    states(
                        observer(
                            onComplete = { disposable = null },
                            onNext = { mutableState.value = it }
                        )
                    )
            }

            override fun onDestroy() {
                disposable?.dispose()
                disposable = null
            }
        }
    )

    return mutableState
}