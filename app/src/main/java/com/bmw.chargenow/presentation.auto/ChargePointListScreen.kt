package com.bmw.chargenow.presentation.auto

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.*
import androidx.lifecycle.DefaultLifecycleObserver
import com.bmw.chargenow.AppLauncher

class ChargePointListScreen(
    carContext: CarContext,
) : Screen(carContext), DefaultLifecycleObserver {

    private sealed class State {
        data object Loading : State()
        data class Data(val chargePoints: List<Any>) : State()
    }

    private val appLauncher = AppLauncher()
    private var state: State = State.Data(listOf(1, 2, 3))

    override fun onGetTemplate(): Template {
        val builder = ListTemplate.Builder()
            .setHeaderAction(Action.BACK)
            .setTitle("Chargepoints")
            .setActionStrip(ActionStrip.Builder().addAction(createChargingAction()).build())

        return when (val currentState = state) {
            is State.Loading -> builder.setLoading(true)
            is State.Data -> {
                val items = currentState.chargePoints.take(6).map { createRowExample() }
                val itemList = ItemList.Builder().addItems(items).build()
                builder.setLoading(false).setSingleList(itemList)
            }
        }.build()
    }

    private fun createRowExample(): Row {
        return Row.Builder()
            .setTitle("Title")
            .addText("Lorem Ipsum")
            .build()
    }

    /**
     * Test this special action/ClickListener with the "restrict none" and "restrict all" DHU commands,
     * see https://developer.android.com/training/cars/testing#dhu-commands.
     */
    private fun createChargingAction(): Action {
        return Action.Builder()
            .setTitle("Open Deeplink")
            .setBackgroundColor(CarColor.DEFAULT)
            .setOnClickListener(
                ParkedOnlyOnClickListener.create {
                    appLauncher.launchPoolDetailsInCurrentApp(carContext, "PoolID: 12345")
                }
            )
            .build()
    }
}

fun ItemList.Builder.addItems(items: List<Item>): ItemList.Builder {
    items.forEach {
        addItem(it)
    }
    return this
}
