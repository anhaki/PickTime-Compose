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
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.ui.theme.PickTimeTheme
import com.anhaki.picktime.utils.PickTimeTextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PickTimeTheme {
                var hour by remember { mutableIntStateOf(18) }
                var minute by remember { mutableIntStateOf(45) }
                var second by remember { mutableIntStateOf(23) }

                var date by remember { mutableIntStateOf(28) }
                var month by remember { mutableIntStateOf(1) }
                var year by remember { mutableIntStateOf(2025) }
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    PickHourMinuteSecond(
                        initialHour = hour,
                        onHourChange = { hour = it },
                        initialMinute = minute,
                        onMinuteChange = { minute = it },
                        initialSecond = second,
                        onSecondChange = { second = it },
                    )
                    PickHourMinute(
                        initialHour = hour,
                        onHourChange = { hour = it },
                        initialMinute = minute,
                        onMinuteChange = { minute = it },
                    )
                    PickDate(
                        initialDate = date,
                        onDateChange = { date = it },
                        initialMonth = month,
                        onMonthChange = { month = it },
                        initialYear = year,
                        onYearChange = { year = it },
                    )
                    Spacer(modifier = Modifier.height(40.dp))

                    Text(text = "Hour = $hour")
                    Text(text = "Minute = $minute")
                    Text(text = "Second = $second")
                    Text(text = "")
                    Text(text = "Date = $date")
                    Text(text = "Month = $month")
                    Text(text = "Year = $year")
                }
            }
        }
    }
}