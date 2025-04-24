package com.anhaki.picktime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.components.NumberWheel

@Composable
fun PickHourMinute(
    initialHour: Int,
    onHourChange: (Int) -> Unit,
    initialMinute: Int,
    onMinuteChange: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberWheel(
                items = (0..23).toList(),
                selectedItem = initialHour - 1,
                onItemSelected = { onHourChange(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            )

            Text(
                text = ":",
                fontSize = 54.sp,
                color = MaterialTheme.colorScheme.primary,
            )

            NumberWheel(
                items = (0..59).toList(),
                selectedItem = initialMinute - 1,
                onItemSelected = { onMinuteChange(it) },
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)
            )
        }
    }
}
