package com.anhaki.picktime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.components.FocusIndicator
import com.anhaki.picktime.components.GenericPickTime
import com.anhaki.picktime.components.NumberWheel
import com.anhaki.picktime.utils.PickTimeFocusIndicator
import com.anhaki.picktime.utils.PickTimeTextStyle

@Composable
fun PickHourMinuteSecond(
    initialHour: Int,
    onHourChange: (Int) -> Unit,
    initialMinute: Int,
    onMinuteChange: (Int) -> Unit,
    initialSecond: Int,
    onSecondChange: (Int) -> Unit,
    selectedTextStyle: PickTimeTextStyle = PickTimeTextStyle(
        color = Color(0xFF404040),
        fontSize = 24.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
    ),
    unselectedTextStyle: PickTimeTextStyle = PickTimeTextStyle(
        color = Color(0xFF9F9F9F),
        fontSize = 18.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
    ),
    verticalSpace: Dp = 10.dp,
    horizontalSpace: Dp = 10.dp,
    containerColor: Color = Color(0xFFFFFFFF),
    isLooping: Boolean = false,
    extraRow: Int = 2,
    focusIndicator: PickTimeFocusIndicator = PickTimeFocusIndicator(
        enabled = true,
        widthFull = false,
        background = Color(0xFFFFFFFF),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(4.dp, Color(0xFFEE4720)),
    )
) {
    val hour = initialHour.coerceIn(0, 23)
    val minute = initialMinute.coerceIn(0, 59)
    val second = initialSecond.coerceIn(0, 59)
    val row = extraRow.coerceIn(1, 5)

    val adjustedSelectedTextStyle = if (selectedTextStyle.fontSize < unselectedTextStyle.fontSize) {
        selectedTextStyle.copy(fontSize = unselectedTextStyle.fontSize)
    } else selectedTextStyle

    GenericPickTime(
        wheels = listOf(
            {
                NumberWheel(
                    items = (0..23).toList(),
                    selectedItem = hour,
                    onItemSelected = onHourChange,
                    space = verticalSpace,
                    selectedTextStyle = adjustedSelectedTextStyle,
                    unselectedTextStyle = unselectedTextStyle,
                    extraRow = row,
                    isLooping = isLooping,
                    overlayColor = containerColor,
                )
            },
            {
                Text(
                    text = ":",
                    style = adjustedSelectedTextStyle.toTextStyle()
                )
            },
            {
                NumberWheel(
                    items = (0..59).toList(),
                    selectedItem = minute,
                    onItemSelected = onMinuteChange,
                    space = verticalSpace,
                    selectedTextStyle = adjustedSelectedTextStyle,
                    unselectedTextStyle = unselectedTextStyle,
                    extraRow = row,
                    isLooping = isLooping,
                    overlayColor = containerColor,
                )
            },
            {
                Text(
                    text = ":",
                    style = adjustedSelectedTextStyle.toTextStyle()
                )
            },
            {
                NumberWheel(
                    items = (0..59).toList(),
                    selectedItem = second,
                    onItemSelected = onSecondChange,
                    space = verticalSpace,
                    selectedTextStyle = adjustedSelectedTextStyle,
                    unselectedTextStyle = unselectedTextStyle,
                    extraRow = row,
                    isLooping = isLooping,
                    overlayColor = containerColor,
                )
            }
        ),
        selectedTextStyle = adjustedSelectedTextStyle,
        verticalSpace = verticalSpace,
        horizontalSpace = horizontalSpace,
        containerColor = containerColor,
        focusIndicator = focusIndicator
    )
}
