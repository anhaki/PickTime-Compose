package com.anhaki.picktime.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.rememberTextMeasurer

/**
 * Measures the width of a specific text string using the given text style.
 * Useful for determining the width of a wheel picker item or focus indicator.
 *
 * @param text The string text to measure.
 * @param textStyle The custom text style applied to the text.
 * @return The width of the measured text as a Float.
 */

@Composable
internal fun measureTextWidth(
    text: String,
    textStyle: PickTimeTextStyle,
): Float {
    val textMeasurer = rememberTextMeasurer()

    val layoutResult = textMeasurer.measure(
        text = "$text ",
        style = textStyle.toTextStyle()
    )
    return layoutResult.size.width.toFloat()
}
