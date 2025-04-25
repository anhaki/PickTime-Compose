package com.anhaki.picktime

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.components.FocusIndicator
import com.anhaki.picktime.components.NumberWheel
import com.anhaki.picktime.utils.PickTimeFocusIndicator
import com.anhaki.picktime.utils.PickTimeTextStyle

@Composable
fun PickHourMinute(
    initialHour: Int,
    onHourChange: (Int) -> Unit,
    initialMinute: Int,
    onMinuteChange: (Int) -> Unit,
    selectedTextStyle: PickTimeTextStyle = PickTimeTextStyle(
        color = Color.Black,
        fontSize = 54.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
    ),
    unselectedTextStyle: PickTimeTextStyle = PickTimeTextStyle(
        color = Color.Gray,
        fontSize = 34.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
    ),
    verticalSpace: Dp = 0.dp,
    horizontalSpace: Dp = 15.dp,
    containerColor: Color = Color.White,
    isLooping: Boolean = true,
    extraRow: Int = 2,
    focusIndicator: PickTimeFocusIndicator = PickTimeFocusIndicator(
        enabled = true,
        fullWidth = false,
        background = Color.LightGray,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(3.dp, Color.DarkGray),
    )
) {
    val hour = initialHour.coerceIn(0, 23)
    val minute = initialMinute.coerceIn(0, 59)
    val row = extraRow.coerceIn(1, 5)

    val adjustedSelectedTextStyle = if (selectedTextStyle.fontSize < unselectedTextStyle.fontSize) {
        selectedTextStyle.copy(fontSize = unselectedTextStyle.fontSize)
    } else selectedTextStyle

    var minContainerWidth by remember { mutableStateOf(0.dp) }

    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(containerColor),
        contentAlignment = Alignment.Center
    ) {
        FocusIndicator(
            focusIndicator = focusIndicator,
            selectedTextStyle = adjustedSelectedTextStyle,
            minWidth = minContainerWidth,
        )
        Row(
            modifier = Modifier
                .onGloballyPositioned {
                    minContainerWidth = with(density) {
                        it.size.width.toDp()
                    }
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberWheel(
                items = (0..23).toList(),
                selectedItem = hour - 1,
                space = verticalSpace,
                onItemSelected = { onHourChange(it) },
                selectedTextStyle = adjustedSelectedTextStyle,
                unselectedTextStyle = unselectedTextStyle,
                extraRow = row,
                isLooping = isLooping,
                overlayColor = containerColor
            )
            Spacer(modifier = Modifier.width(horizontalSpace))
            Text(
                text = ":",
                style = adjustedSelectedTextStyle.toTextStyle()
            )
            Spacer(modifier = Modifier.width(horizontalSpace))
            NumberWheel(
                items = (0..59).toList(),
                selectedItem = minute - 1,
                onItemSelected = { onMinuteChange(it) },
                space = verticalSpace,
                selectedTextStyle = adjustedSelectedTextStyle,
                unselectedTextStyle = unselectedTextStyle,
                extraRow = row,
                isLooping = isLooping,
                overlayColor = containerColor,
            )
        }
    }
}
