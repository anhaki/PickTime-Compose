package com.anhaki.picktime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anhaki.picktime.ui.theme.PickTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickTimeTheme {
                var hour by remember { mutableIntStateOf(0) }
                var minute by remember { mutableIntStateOf(0) }
                var second by remember { mutableIntStateOf(0) }
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(200.dp))
                    PickHourMinuteSecond(
                        initialHour = hour,
                        onHourChange = { hour = it },
                        initialMinute = minute,
                        onMinuteChange = { minute = it },
                        initialSecond = second,
                        onSecondChange = { second = it },
                    )
//                    PickHourMinute(
//                        initialHour = hour,
//                        onHourChange = { hour = it },
//                        initialMinute = minute,
//                        onMinuteChange = { minute = it },
//                    )
                    Spacer(modifier = Modifier.height(40.dp))

                    Text(text = "Hour = $hour")
                    Text(text = "Minute = $minute")
                    Text(text = "Second = $second")
                }
            }
        }
    }
}