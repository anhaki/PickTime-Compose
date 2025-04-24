package com.anhaki.picktime.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.anhaki.picktime.utils.PickTimeTextStyle
import com.anhaki.picktime.utils.measureTextHeights
import kotlinx.coroutines.launch


@Composable
fun NumberWheel(
    modifier: Modifier = Modifier,
    items: List<Int>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    space: Dp,
    selectedTextStyle: PickTimeTextStyle,
    unselectedTextStyle: PickTimeTextStyle,
    extraRow: Int,
    isLooping: Boolean,
) {
    val listState =
        if(isLooping){
            rememberLazyListState(nearestIndexTarget(Int.MAX_VALUE / 2, (selectedItem - extraRow) + 1, items.size))
        } else{
            rememberLazyListState(initialFirstVisibleItemIndex = selectedItem)
        }

    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    val (selectedTextLineHeightPx, unselectedTextLineHeightPx) = measureTextHeights(
        selectedTextStyle = selectedTextStyle,
        unselectedTextStyle = unselectedTextStyle
    )

    val selectedTextLineHeightDp = with(density){ selectedTextLineHeightPx.toDp() }
    val unselectedTextLineHeightDp = with(density){ unselectedTextLineHeightPx.toDp() }

    val wheelHeight = (unselectedTextLineHeightDp * (extraRow * 2)) + (space * (extraRow * 2 + 2)) + selectedTextLineHeightDp

    val maxOffset = with(density) { unselectedTextLineHeightPx + space.toPx() }

    val firstIndex by remember { derivedStateOf { listState.firstVisibleItemIndex } }
    val firstVisibleOffset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val isScrolling by remember { derivedStateOf { listState.isScrollInProgress } }
    val progress = (firstVisibleOffset / maxOffset).coerceIn(0f, 1f)


    val sizeInterpolator = { index: Int ->
        when (index) {
            firstIndex -> transition(selectedTextStyle.fontSize.value, unselectedTextStyle.fontSize.value, progress)
            firstIndex + 1 -> transition(unselectedTextStyle.fontSize.value, selectedTextStyle.fontSize.value, progress)
            else -> unselectedTextStyle.fontSize.value
        }
    }

    val heightInterpolator = { index: Int ->
        when (index) {
            firstIndex -> transition(selectedTextLineHeightPx, unselectedTextLineHeightPx, progress)
            firstIndex + 1 -> transition(unselectedTextLineHeightPx, selectedTextLineHeightPx, progress)
            else -> unselectedTextLineHeightPx
        }
    }

    val colorInterpolator = { index: Int ->
        when (index) {
            firstIndex -> transition(selectedTextStyle.color, unselectedTextStyle.color, progress)
            firstIndex + 1 -> transition(unselectedTextStyle.color, selectedTextStyle.color, progress)
            else -> unselectedTextStyle.color
        }
    }

    LaunchedEffect(firstVisibleOffset) {
        onItemSelected(items[(firstIndex + if (firstVisibleOffset > maxOffset / 2) extraRow + 1 else extraRow)  % items.size])
    }

    LaunchedEffect(isScrolling) {
        if (!isScrolling) {
            coroutineScope.launch {
                listState.animateScrollToItem(firstIndex + if (firstVisibleOffset > maxOffset / 2) 1 else 0)
            }
        }
    }

    Box(
        modifier = modifier
            .height(wheelHeight),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space),
            contentPadding = PaddingValues(top = space, bottom = if(isLooping) space else (space * 2) + unselectedTextLineHeightDp)
        ) {
            if(!isLooping) {
                item {
                    Text(
                        modifier = Modifier.height(unselectedTextLineHeightDp),
                        text = " ",
                        color = Color.Transparent,
                        fontSize = unselectedTextStyle.fontSize,
                        fontWeight = FontWeight.Normal,
                        fontFamily = unselectedTextStyle.fontFamily
                    )
                }
            }

            items(count = if(isLooping) Int.MAX_VALUE else items.size, itemContent = {
                val itemIndex = it % items.size
                val item = items[itemIndex]
                val countIndex = if(isLooping) it % Int.MAX_VALUE else it % items.size

                Text(
                    modifier = Modifier
                        .height(with(density){ heightInterpolator(countIndex - extraRow).toDp() }),
                    text = item.toString().padStart(2, '0'),
                    color = colorInterpolator(countIndex - extraRow),
                    fontSize = sizeInterpolator(countIndex - extraRow).sp,
                    fontWeight = if (selectedItem == items[itemIndex] - 1) selectedTextStyle.fontWeight else unselectedTextStyle.fontWeight,
                    fontFamily = if (selectedItem == items[itemIndex] - 1) selectedTextStyle.fontFamily else unselectedTextStyle.fontFamily
                )
            })
        }
    }
}

fun transition(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction
}

fun transition(start: Color, end: Color, fraction: Float): Color {
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