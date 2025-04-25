package com.anhaki.picktime.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
internal fun GradientOverlay(
    modifier: Modifier,
    color: Color,
    height: Dp
) {
    Box (
        modifier = modifier
    ){
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        .5F to Color.Transparent,
                        .8F to color.copy(alpha = .8f),
                        1F to color
                    )
                )
                .height(height)
                .fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        0F to color,
                        .2F to color.copy(alpha = .8f),
                        .5F to Color.Transparent
                    )
                )
                .height(height)
                .fillMaxWidth()
        )
    }
}