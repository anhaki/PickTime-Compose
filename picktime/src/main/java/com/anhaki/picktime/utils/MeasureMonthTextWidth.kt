package com.anhaki.picktime.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.rememberTextMeasurer

@Composable
internal fun measureMonthTextWidth(
    textStyle: PickTimeTextStyle,
): Float {
    val textMeasurer = rememberTextMeasurer()

    val layoutResult = textMeasurer.measure(
        text = "September ",
        style = textStyle.toTextStyle()
    )
    return layoutResult.size.width.toFloat()
}
