package com.anhaki.picktime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.components.GenericPickTime
import com.anhaki.picktime.components.NumberWheel
import com.anhaki.picktime.utils.PickTimeFocusIndicator
import com.anhaki.picktime.utils.PickTimeTextStyle

/**
 * A composable function that creates a customizable layout consisting of hour, minute, and second wheel pickers,
 * complete with adjustable styling, looping behavior, and focus indicators.
 *
 * @param initialHour The initial value of the hour wheel picker.
 * @param onHourChange The callback function invoked when the hour value changes.
 * @param initialMinute The initial value of the minute wheel picker.
 * @param onMinuteChange The callback function invoked when the minute value changes.
 * @param initialSecond The initial value of the second wheel picker.
 * @param onSecondChange The callback function invoked when the second value changes.
 * @param selectedTextStyle The style of the selected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param unselectedTextStyle The style of the unselected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param verticalSpace The vertical spacing between each item of the wheel picker.
 * @param horizontalSpace The horizontal spacing between the wheel pickers and the colon.
 * @param containerColor The color of the container (background and gradient overlay).
 * @param isLooping Whether the wheel pickers should loop their values.
 * @param extraRow The number of extra rows shown on each side (top and bottom).
 * @param focusIndicator The focus indicator displayed at the center of the wheel pickers, using [PickTimeFocusIndicator] including `enabled`, `widthFull`, `background`, `shape`, and `border`.
 *
 * Note: The function internally clamps [initialHour] between 0–23, [initialMinute] between 0–59 and [initialSecond] between 0-59.
 */

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
