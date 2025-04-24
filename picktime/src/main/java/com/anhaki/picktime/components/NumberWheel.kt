package com.anhaki.picktime.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@Composable
fun NumberWheel(
    items: List<Int>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: Int = 36,
    selectedFontSize: Int = 54,
    padding: Int = 0,
) {
    val listState =
        rememberLazyListState(nearestIndexTarget(Int.MAX_VALUE / 2, selectedItem, items.size))

    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    val textHeight = fontSize * 1.5
    val selectedTextHeight = selectedFontSize * 1.5
    val maxOffset = with(density) { (textHeight + padding).dp.toPx() }

    val firstIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val firstVisibleOffset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val isScrolling by remember { derivedStateOf { listState.isScrollInProgress } }
    val progress = (firstVisibleOffset / maxOffset).coerceIn(0f, 1f)

    val textColor = MaterialTheme.colorScheme.primaryContainer
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.colorScheme.primaryContainer

    val sizeInterpolator = { index: Int ->
        when (index) {
            firstIndex -> lerp(selectedFontSize.toFloat(), fontSize.toFloat(), progress)
            firstIndex + 1 -> lerp(fontSize.toFloat(), selectedFontSize.toFloat(), progress)
            else -> fontSize.toFloat()
        }
    }

    val heightInterpolator = { index: Int ->
        when (index) {
            firstIndex -> lerp(selectedTextHeight, textHeight, progress.toDouble())
            firstIndex + 1 -> lerp(textHeight, selectedTextHeight, progress.toDouble())
            else -> textHeight
        }
    }

    val colorInterpolator = { index: Int ->
        when (index) {
            firstIndex -> lerp(selectedColor, unselectedColor, progress)
            firstIndex + 1 -> lerp(unselectedColor, selectedColor, progress)
            else -> textColor
        }
    }

    Box(
        modifier = modifier
            .height(((textHeight * 2) + (padding * 4) + selectedTextHeight).dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(padding.dp),
            contentPadding = PaddingValues(vertical = padding.dp)
        ) {
            items(count = Int.MAX_VALUE, itemContent = {
                val itemIndex = it % items.size
                val item = items[itemIndex]
                val countIndex = it % Int.MAX_VALUE

                Text(
                    modifier = Modifier.height(heightInterpolator(countIndex - 1).dp),
                    text = item.toString().padStart(2, '0'),
                    color = colorInterpolator(countIndex - 1),
                    fontSize = sizeInterpolator(countIndex - 1).sp,
                    fontWeight = if (selectedItem == items[itemIndex] - 1) FontWeight.Black else FontWeight.Bold
                )
            })
        }
    }

    LaunchedEffect(firstVisibleOffset) {
        onItemSelected(items[(firstIndex + if (firstVisibleOffset > maxOffset / 2) 2 else 1) % items.size])
    }

    LaunchedEffect(isScrolling) {
        if (!isScrolling) {
            coroutineScope.launch {
                listState.animateScrollToItem(firstIndex + if (firstVisibleOffset > maxOffset / 2) 1 else 0)
            }
        }
    }
}

fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}

fun lerp(start: Double, end: Double, fraction: Double): Double {
    return start + (end - start) * fraction
}

fun lerp(start: Color, end: Color, fraction: Float): Color {
    return Color(
        (start.red + (end.red - start.red) * fraction),
        (start.green + (end.green - start.green) * fraction),
        (start.blue + (end.blue - start.blue) * fraction),
        (start.alpha + (end.alpha - start.alpha) * fraction)
    )
}

fun nearestIndexTarget(initialIndex: Int, target: Int, size: Int): Int {
    val upperLimit = (initialIndex / size) * size + target
    val lowerLimit = upperLimit + size
    return if ((initialIndex - upperLimit) <= (lowerLimit - initialIndex)) {
        upperLimit
    } else {
        lowerLimit
    }
}