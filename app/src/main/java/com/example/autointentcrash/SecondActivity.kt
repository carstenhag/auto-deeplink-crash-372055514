package com.example.autointentcrash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bmw.chargenow.AppLauncher
import com.example.autointentcrash.ui.theme.AutoIntentCrashTheme

class SecondActivity : ComponentActivity() {
    companion object {
        fun newIntent(context: Context?): Intent {
            return Intent(context, SecondActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appLauncher = AppLauncher()

        setContent {
            val context = LocalContext.current
            AutoIntentCrashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Button(
                        onClick = {
                            appLauncher.launchPoolDetailsInCurrentApp(context, "1234567890")
                        },
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Open Deeplink from handheld itself")
                    }
                }
            }
        }
    }
}
