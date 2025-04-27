package com.anhaki.picktime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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

/**
 * A composable function that creates a customizable layout consisting of day, month, and year wheel pickers,
 * complete with adjustable styling, looping behavior, and focus indicators.
 *
 * @param initialDay The initial value of the day wheel picker.
 * @param onDayChange The callback function invoked when the day value changes.
 * @param dayRange The valid range of days, defaulting to 1..31.
 * @param initialMonth The initial selected month value (1-based index).
 * @param onMonthChange The callback function invoked when the month value changes.
 * @param monthList The list of month names to be displayed, defaulting to `English month names`.
 * @param initialYear The initial value of the year wheel picker.
 * @param onYearChange The callback function invoked when the year value changes.
 * @param yearRange The valid range of years, defaulting to 1990..2060.
 * @param selectedTextStyle The style of the selected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param unselectedTextStyle The style of the unselected text, using [PickTimeTextStyle] including `color`, `fontSize`, `fontFamily`, and `fontWeight`.
 * @param verticalSpace The vertical spacing between each item of the wheel picker.
 * @param horizontalSpace The horizontal spacing between the wheel pickers and the colon.
 * @param containerColor The color of the container (background and gradient overlay).
 * @param isLooping Whether the wheel pickers should loop their values.
 * @param extraRow The number of extra rows shown on each side (top and bottom).
 * @param focusIndicator The focus indicator displayed at the center of the wheel pickers, using [PickTimeFocusIndicator] including `enabled`, `widthFull`, `background`, `shape`, and `border`.
 *
 * Note:
 * - The function internally clamps `initialDay` within the given `dayRange`.
 * - The `initialMonth` is clamped between 1 and the size of `monthList`.
 * - The `initialYear` is clamped within the given `yearRange`.
 */

@Composable
fun PickDate(
    initialDay: Int,
    dayRange: IntRange = (1..31),
    onDayChange: (Int) -> Unit,
    initialMonth: Int,
    monthList: List<String> = listOf(
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    ),
    onMonthChange: (Int) -> Unit,
    initialYear: Int,
    yearRange: IntRange = (1990..2060),
    onYearChange: (Int) -> Unit,
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
    verticalSpace: Dp = 12.dp,
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
    val displayedDay = initialDay.coerceIn(dayRange.first(), dayRange.last())
    val displayedMonth = initialMonth.coerceIn(1, monthList.size)
    val displayedYear = initialYear.coerceIn(yearRange.first(), yearRange.last())

    val row = extraRow.coerceIn(1, 5)

    val adjustedSelectedTextStyle = if (selectedTextStyle.fontSize < unselectedTextStyle.fontSize) {
        selectedTextStyle.copy(fontSize = unselectedTextStyle.fontSize)
    } else selectedTextStyle


    GenericPickTime(
        selectedTextStyle = adjustedSelectedTextStyle,
        verticalSpace = verticalSpace,
        containerColor = containerColor,
        focusIndicator = focusIndicator
    ) {
        NumberWheel(
            items = dayRange.toList(),
            selectedItem = displayedDay,
            onItemSelected = onDayChange,
            space = verticalSpace,
            selectedTextStyle = adjustedSelectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor,
        )
        Spacer(modifier = Modifier.width(horizontalSpace))
        StringWheel(
            items = monthList,
            selectedItem = displayedMonth,
            onItemSelected = onMonthChange,
            space = verticalSpace,
            selectedTextStyle = adjustedSelectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor
        )
        Spacer(modifier = Modifier.width(horizontalSpace))
        NumberWheel(
            items = yearRange.toList(),
            selectedItem = displayedYear,
            onItemSelected = onYearChange,
            space = verticalSpace,
            selectedTextStyle = adjustedSelectedTextStyle,
            unselectedTextStyle = unselectedTextStyle,
            extraRow = row,
            isLooping = isLooping,
            overlayColor = containerColor,
        )
    }
}
