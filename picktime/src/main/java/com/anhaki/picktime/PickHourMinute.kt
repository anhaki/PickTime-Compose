package com.anhaki.picktime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.components.GenericPickTime
import com.anhaki.picktime.components.NumberWheel
import com.anhaki.picktime.components.StringWheel
import com.anhaki.picktime.utils.PickTimeFocusIndicator
import com.anhaki.picktime.utils.PickTimeTextStyle
import com.anhaki.picktime.utils.TimeFormat

/**
 * A composable function that creates a customizable layout consisting of hour and minute wheel pickers,
 * complete with adjustable styling, looping behavior, and focus indicators.
 *
 * @param initialHour The initial value of the hour wheel picker.
 * @param onHourChange The callback function invoked when the hour value changes.
 * @param initialMinute The initial value of the minute wheel picker.
 * @param onMinuteChange The callback function invoked when the minute value changes.
 * @param selectedTextStyle The style of the selected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param unselectedTextStyle The style of the unselected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param verticalSpace The vertical spacing between each item of the wheel picker.
 * @param horizontalSpace The horizontal spacing between the wheel pickers and the colon.
 * @param containerColor The color of the container (background and gradient overlay).
 * @param isLooping Whether the wheel pickers should loop their values.
 * @param extraRow The number of extra rows shown on each side (top and bottom).
 * @param focusIndicator The focus indicator displayed at the center of the wheel pickers, using [PickTimeFocusIndicator] including `enabled`, `widthFull`, `background`, `shape`, and `border`.
 *
 * Note: The function internally clamps `initialHour` between 0–23 and `initialMinute` between 0–59.
 */

@Composable
fun PickHourMinute(
    initialHour: Int,
    onHourChange: (Int) -> Unit,
    initialMinute: Int,
    onMinuteChange: (Int) -> Unit,
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
    timeFormat: TimeFormat = TimeFormat.HOUR_24,
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
    val hourRange = when(timeFormat){
        TimeFormat.HOUR_24 -> {
            (0..23)
        }
        TimeFormat.HOUR_12 -> {
            (1..12)
        }
    }
    val minuteRange = (0..59)
    val displayedHour = when (timeFormat) {
        TimeFormat.HOUR_24 -> initialHour.coerceIn(0, 23)
        TimeFormat.HOUR_12 -> {
            when (initialHour % 12) {
                0 -> 12
                else -> initialHour % 12
            }
        }
    }

    val displayedMinute = initialMinute.coerceIn(0, 59)

    val initialAmPm = when {
        initialHour in 0..11 -> 1 // AM
        else -> 2 // PM
    }

    val row = extraRow.coerceIn(1, 5)

    val adjustedSelectedTextStyle = if (selectedTextStyle.fontSize < unselectedTextStyle.fontSize) {
        selectedTextStyle.copy(fontSize = unselectedTextStyle.fontSize)
    } else selectedTextStyle

    GenericPickTime(
        selectedTextStyle = adjustedSelectedTextStyle,
        verticalSpace = verticalSpace,
        containerColor = containerColor,
        focusIndicator = focusIndicator
    ){
        NumberWheel(
            items = hourRange.toList(),
            selectedItem = displayedHour,
            onItemSelected = { selectedHour ->
                val newHour = when (timeFormat) {
                    TimeFormat.HOUR_24 -> selectedHour
                    TimeFormat.HOUR_12 -> {
                        val amPmAdjustedHour = if (initialAmPm == 1) { // AM
                            if (selectedHour == 12) 0 else selectedHour
                        } else { // PM
                            if (selectedHour == 12) 12 else selectedHour + 12
                        }
                        amPmAdjustedHour
                    }
                }
                onHourChange(newHour)
            },
            space = verticalSpace,
            selectedTextStyle = adjustedSelectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor,
        )
        Spacer(modifier = Modifier.width(horizontalSpace))
        Text(
            text = ":",
            style = adjustedSelectedTextStyle.toTextStyle()
        )
        Spacer(modifier = Modifier.width(horizontalSpace))
        NumberWheel(
            items = minuteRange.toList(),
            selectedItem = displayedMinute,
            onItemSelected = onMinuteChange,
            space = verticalSpace,
            selectedTextStyle = adjustedSelectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor,
        )
        if(timeFormat == TimeFormat.HOUR_12){
            Spacer(modifier = Modifier.width(horizontalSpace))
            StringWheel(
                items = listOf("AM", "PM"),
                selectedItem = initialAmPm,
                onItemSelected = {
                    val adjustedHour = when (it) {
                        1 -> { // AM
                            if (initialHour in 12..23) initialHour - 12 else initialHour
                        }
                        2 -> { // PM
                            if (initialHour in 0..11) {
                                if (initialHour == 0) 12 else initialHour + 12
                            } else initialHour
                        }
                        else -> initialHour
                    }
                    onHourChange(adjustedHour)
                },
                space = verticalSpace,
                selectedTextStyle = adjustedSelectedTextStyle,
                unselectedTextStyle = unselectedTextStyle,
                extraRow = row,
                isLooping = false,
                overlayColor = containerColor
            )
        }
    }
}
