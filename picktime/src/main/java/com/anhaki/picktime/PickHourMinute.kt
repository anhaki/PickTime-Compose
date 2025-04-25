package com.anhaki.picktime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.components.NumberWheel
import com.anhaki.picktime.utils.PickTimeTextStyle

@Composable
fun PickHourMinute(
    initialHour: Int,
    onHourChange: (Int) -> Unit,
    initialMinute: Int,
    onMinuteChange: (Int) -> Unit,
    space: Dp = 20.dp,
    selectedTextStyle: PickTimeTextStyle = PickTimeTextStyle(
        color = Color.Black,
        fontSize = 42.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
    ),
    unselectedTextStyle: PickTimeTextStyle = PickTimeTextStyle(
        color = Color.Gray,
        fontSize = 26.sp,
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
    ),
    extraRow: Int = 2,
    isLooping: Boolean = true,
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
                modifier = Modifier
                    .weight(1f),
                items = (0..23).toList(),
                selectedItem = initialHour - 1,
                space = space,
                onItemSelected = { onHourChange(it) },
                selectedTextStyle = selectedTextStyle,
                unselectedTextStyle = unselectedTextStyle,
                extraRow = extraRow,
                isLooping = isLooping,
            )

            Text(
                text = ":",
                fontSize = 54.sp,
                color = MaterialTheme.colorScheme.primary,
            )

            NumberWheel(
                modifier = Modifier
                    .weight(1f),
                items = (0..59).toList(),
                selectedItem = initialMinute - 1,
                onItemSelected = { onMinuteChange(it) },
                space = space,
                selectedTextStyle = selectedTextStyle,
                unselectedTextStyle = unselectedTextStyle,
                extraRow = extraRow,
                isLooping = isLooping,
            )
        }
    }
}
