package com.anhaki.picktime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anhaki.picktime.ui.theme.PickTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickTimeTheme{
                var hour by remember { mutableIntStateOf(4) }
                var minute by remember { mutableIntStateOf(10) }
                Column (
                    modifier = Modifier.background(Color.White).fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    PickHourMinute(
                        initialHour = hour,
                        onHourChange = { hour = it },
                        initialMinute = minute,
                        onMinuteChange = { minute = it },
                    )
                    Text(text = "Hour = $hour")
                    Text(text = "Hour = $minute")
                }
            }
        }
    }
}