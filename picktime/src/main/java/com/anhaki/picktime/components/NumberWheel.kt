package com.anhaki.picktime.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anhaki.picktime.utils.PickTimeTextStyle


@Composable
internal fun NumberWheel(
    modifier: Modifier = Modifier,
    items: List<Int>,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit,
    space: Dp,
    selectedTextStyle: PickTimeTextStyle,
    unselectedTextStyle: PickTimeTextStyle,
    extraRow: Int,
    isLooping: Boolean,
    overlayColor: Color
) {
    Wheel(
        modifier = modifier,
        items = items,
        selectedItem = items.indexOf(selectedItem),
        onItemSelected = { index -> onItemSelected(items[index]) },
        space = space,
        selectedTextStyle = selectedTextStyle,
        unselectedTextStyle = unselectedTextStyle,
        extraRow = extraRow,
        isLooping = isLooping,
        overlayColor = overlayColor,
        itemToString = { it.toString().padStart(2, '0') },
        longestText = items.maxBy { it }.toString()
    )
}
