package com.anhaki.picktime.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.rememberTextMeasurer

@Composable
fun measureTextHeights(
    selectedTextStyle: PickTimeTextStyle,
    unselectedTextStyle: PickTimeTextStyle
): Pair<Float, Float> {
    val textMeasurer = rememberTextMeasurer()

    val selectedLayoutResult = textMeasurer.measure(
        text = " ",
        style = selectedTextStyle.toTextStyle()
    )

    val unselectedLayoutResult = textMeasurer.measure(
        text = " ",
        style = unselectedTextStyle.toTextStyle()
    )

    return Pair(
        selectedLayoutResult.size.height.toFloat(),
        unselectedLayoutResult.size.height.toFloat()
    )
}
