package com.anhaki.picktime.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anhaki.picktime.utils.PickTimeFocusIndicator
import com.anhaki.picktime.utils.PickTimeTextStyle
import com.anhaki.picktime.utils.measureTextHeight

/**
 * A composable that displays a focus indicator (such as a border and background)
 * to highlight the selected item in a wheel picker.
 *
 * @param focusIndicator Defines the appearance and behavior of the focus indicator (border, background, shape).
 * @param selectedTextStyle The text style used for the selected item (used to calculate height).
 * @param minWidth The minimum width of the indicator if widthFull is false.
 * @param verticalSpace Additional vertical spacing added to the indicator height.
 */

@Composable
internal fun FocusIndicator(
    focusIndicator: PickTimeFocusIndicator,
    selectedTextStyle: PickTimeTextStyle,
    minWidth: Dp,
    verticalSpace: Dp,
) {
    if (focusIndicator.enabled) {
        val density = LocalDensity.current

        val selectedTextLineHeightPx = measureTextHeight(selectedTextStyle)
        val selectedTextLineHeightDp = with(density) { selectedTextLineHeightPx.toDp() }

        var modifier = if (focusIndicator.border.width > 0.dp) Modifier.border(
            focusIndicator.border,
            focusIndicator.shape
        ) else Modifier

        modifier = modifier
            .clip(focusIndicator.shape)
            .background(focusIndicator.background)
            .padding(horizontal = 15.dp)
            .height(selectedTextLineHeightDp + verticalSpace)

        Box(
            modifier =
            if (focusIndicator.widthFull) {
                modifier.fillMaxWidth()
            } else {
                modifier.width(minWidth)
            }
        )
    }
}