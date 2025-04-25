package com.anhaki.picktime.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class PickTimeFocusIndicator (
    val enabled: Boolean,
    val widthFull: Boolean = true,
    val background: Color = Color(0xFFFFFFFF),
    val shape: Shape = RectangleShape,
    val border: BorderStroke = BorderStroke(0.dp, Color(0xFFFFFFFF))
)