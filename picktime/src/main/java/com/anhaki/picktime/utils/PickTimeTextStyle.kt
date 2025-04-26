package com.anhaki.picktime.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * A custom text style holder used across pickers for consistent styling of wheel items.
 *
 * @param color The color of the text.
 * @param fontSize The size of the text.
 * @param fontFamily The font family used.
 * @param fontWeight The weight (thickness) of the font.
 */

data class PickTimeTextStyle(
    val color: Color = Color(0xFF404040),
    val fontSize: TextUnit = 24.sp,
    val fontFamily: FontFamily = FontFamily.Default,
    val fontWeight: FontWeight = FontWeight.Normal
) {
    /**
     * Converts this custom PickTimeTextStyle into a standard Compose TextStyle object
     * to be used in text composables.
     *
     * @return A TextStyle object matching the properties of PickTimeTextStyle.
     */
    fun toTextStyle(): TextStyle {
        return TextStyle(
            color = color,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    }
}

