package com.bmw.chargenow.presentation.auto

import android.content.Intent
import androidx.car.app.Screen
import androidx.car.app.Session

class DCSCarAppSession : Session() {
    override fun onCreateScreen(intent: Intent): Screen {
        return ChargePointListScreen(carContext)
    }
}
