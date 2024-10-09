package com.example.autointentcrash

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.autointentcrash.ui.theme.AutoIntentCrashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentData = intent.data
        when (intentData?.host) {
            getString(R.string.DEEP_LINK_HOST_POOL_DETAILS) -> {
                intentData.getQueryParameter("dcsPoolId")?.let { poolId ->
                    Toast.makeText(this, poolId, Toast.LENGTH_LONG).show()
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            AutoIntentCrashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Button(
                        onClick = {
                            context.startActivity(SecondActivity.newIntent(context))
                        },
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text("Open second activity")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutoIntentCrashTheme {
        Greeting("Android")
    }
}
