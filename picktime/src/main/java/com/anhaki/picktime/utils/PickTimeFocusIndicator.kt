package com.anhaki.picktime.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

data class PickTimeFocusIndicator (
    val enabled: Boolean,
    val fullWidth: Boolean,
    val background: Color,
    val shape: Shape,
    val border: BorderStroke
)