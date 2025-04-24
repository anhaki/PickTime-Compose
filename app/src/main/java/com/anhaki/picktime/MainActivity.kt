package com.anhaki.picktime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.anhaki.picktime.ui.theme.PickTImeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickTImeTheme {
                var hour by remember { mutableIntStateOf(4) }
                var minute by remember { mutableIntStateOf(10) }
                Column {
                    PickHourMinute(
                        initialHour = hour,
                        onHourChange = { hour = it },
                        initialMinute = minute,
                        onMinuteChange = { minute = it },
                    )
                    Text(text = hour.toString())
                    Text(text = minute.toString())
                }
            }
        }
    }
}