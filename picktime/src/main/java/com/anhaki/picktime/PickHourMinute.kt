package com.anhaki.picktime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    verticalSpace: Dp = 20.dp,
    horizontalSpace: Dp = 0.dp,
    containerColor: Color = Color.White,
    isLooping: Boolean = true,
    extraRow: Int = 2,
) {
    val hour = initialHour.coerceIn(0, 23)
    val minute = initialMinute.coerceIn(0, 59)
    val row = extraRow.coerceIn(1, 5)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NumberWheel(
            items = (0..23).toList(),
            selectedItem = hour - 1,
            space = verticalSpace,
            onItemSelected = { onHourChange(it) },
            selectedTextStyle = selectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor
        )
        Spacer(modifier = Modifier.width(horizontalSpace))
        Text(
            text = ":",
            style = selectedTextStyle.toTextStyle()
        )
        Spacer(modifier = Modifier.width(horizontalSpace))
        NumberWheel(
            items = (0..59).toList(),
            selectedItem = minute - 1,
            onItemSelected = { onMinuteChange(it) },
            space = verticalSpace,
            selectedTextStyle = selectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor,
        )
    }
}
