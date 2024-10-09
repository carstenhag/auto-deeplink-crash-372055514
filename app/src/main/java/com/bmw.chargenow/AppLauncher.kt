package com.bmw.chargenow

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.autointentcrash.R

class AppLauncher {

    fun launchPoolDetailsInCurrentApp(context: Context, poolId: String) {
        val deeplinkUri = constructPoolDetailsUri(
            listOf(
                context.getString(R.string.DEEP_LINK_SCHEME)
            ),
            context.getString(R.string.DEEP_LINK_HOST_POOL_DETAILS),
            poolId
        ).first()

        val intent = Intent(Intent.ACTION_VIEW)
            .setData(deeplinkUri)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun constructPoolDetailsUri(schemeList: List<String>, host: String, poolId: String): List<Uri> {
        return schemeList.map { scheme ->
            "$scheme://$host?dcsPoolId=$poolId"
        }.map {
            Uri.parse(it)
        }
    }
}
