package com.bmw.chargenow.presentation.auto

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import androidx.car.app.CarAppService
import androidx.car.app.Session
import androidx.car.app.validation.HostValidator

class DCSCarAppService : CarAppService() {
    override fun onCreateSession(): Session {
        return DCSCarAppSession()
    }

    /**
     * See https://developer.android.com/reference/androidx/car/app/CarAppService#createHostValidator()
     */
    @SuppressLint("PrivateResource")
    override fun createHostValidator(): HostValidator {
        return if (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0) {
            HostValidator.ALLOW_ALL_HOSTS_VALIDATOR
        } else {
            HostValidator.Builder(applicationContext)
                .addAllowedHosts(androidx.car.app.R.array.hosts_allowlist_sample)
                .build()
        }
    }
}
