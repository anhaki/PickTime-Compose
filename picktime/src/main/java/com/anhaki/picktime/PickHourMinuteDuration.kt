package com.anhaki.picktime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
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
 * A composable function that creates a customizable duration picker consisting of hour and minute wheel pickers,
 * with an enforced maximum total duration. The minute range dynamically adjusts based on the selected hour
 * to ensure the combined value never exceeds [maxValue].
 *
 * @param initialHour The initial value of the hour wheel picker.
 * @param onHourChange The callback function invoked when the hour value changes.
 * @param initialMinute The initial value of the minute wheel picker.
 * @param onMinuteChange The callback function invoked when the minute value changes. Also called automatically
 *   when the minute range shrinks due to an hour change, with the clamped minute value.
 * @param maxValue The maximum total duration in minutes (e.g., `4 * 60 + 30` for 4h30m). Defaults to `23 * 60 + 59` (23h59m).
 *   The hour range is derived as `0..(maxValue / 60)` and the minute range adjusts per selected hour.
 * @param selectedTextStyle The style of the selected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param unselectedTextStyle The style of the unselected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param verticalSpace The vertical spacing between each item of the wheel picker.
 * @param horizontalSpace The horizontal spacing between the wheel pickers and the colon.
 * @param containerColor The color of the container (background and gradient overlay).
 * @param isLooping Whether the wheel pickers loop infinitely.
 * @param extraRow The number of extra rows shown on each side (top and bottom).
 * @param focusIndicator The focus indicator displayed at the center of the wheel pickers, using [PickTimeFocusIndicator] including `enabled`, `widthFull`, `background`, `shape`, and `border`.
 *
 * Note: The function internally clamps `initialHour` between 0 and `maxValue / 60` (max 23),
 * and `initialMinute` between 0 and the remaining minutes for the selected hour.
 */

@Composable
fun PickHourMinuteDuration(
    initialHour: Int,
    onHourChange: (Int) -> Unit,
    initialMinute: Int,
    onMinuteChange: (Int) -> Unit,
    maxValue: Int = 23*60 + 59,
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
    val maxHour = (maxValue / 60).coerceIn(0, 23)
    val hourRange = 0..maxHour
    val displayedHour = initialHour.coerceIn(0, maxHour)
    val restMinutes = maxValue - displayedHour * 60
    val minuteRange = if (restMinutes > 0) 0..restMinutes.coerceAtMost(59) else 0..0
    val displayedMinute = initialMinute.coerceIn(minuteRange.first, minuteRange.last)

    LaunchedEffect(minuteRange) {
        onMinuteChange(displayedMinute)
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
            onItemSelected = onHourChange,
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
        key(minuteRange) {
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
        }
    }
}
