package com.anhaki.picktime.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

data class PickTimeTextStyle(
    val color: Color,
    val fontSize: TextUnit,
    val fontFamily: FontFamily,
    val fontWeight: FontWeight
) {
    fun toTextStyle(): TextStyle {
        return TextStyle(
            color = color,
            fontSize = fontSize,
            fontFamily = fontFamily,
            fontWeight = fontWeight
        )
    }
}

