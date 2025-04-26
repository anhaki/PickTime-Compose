package com.anhaki.picktime.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.rememberTextMeasurer

/**
 * Measures the height of a text using the given text style.
 * This is useful for calculating the approximate height needed for a wheel picker item.
 *
 * @param textStyle The custom text style used for measuring.
 * @return The estimated text height as a Float, scaled by 0.6 for visual adjustment.
 */

@Composable
internal fun measureTextHeight(
    textStyle: PickTimeTextStyle,
): Float {
    val textMeasurer = rememberTextMeasurer()

    val layoutResult = textMeasurer.measure(
        text = " \n ",
        style = textStyle.toTextStyle()
    )

    return (layoutResult.size.height * 0.6).toFloat()
}
