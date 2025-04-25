package com.anhaki.picktime.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.rememberTextMeasurer

@Composable
internal fun measureTextWidth(
    selectedTextStyle: PickTimeTextStyle,
): Float {
    val textMeasurer = rememberTextMeasurer()

    val selectedLayoutResult = textMeasurer.measure(
        text = " ",
        style = selectedTextStyle.toTextStyle()
    )
    return selectedLayoutResult.size.width.toFloat()
}
