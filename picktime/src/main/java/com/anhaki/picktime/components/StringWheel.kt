package com.anhaki.picktime.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anhaki.picktime.utils.PickTimeTextStyle


@Composable
internal fun StringWheel(
    modifier: Modifier = Modifier,
    items: List<String>,
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
        selectedItem = selectedItem - 1,
        onItemSelected = { index -> onItemSelected(index + 1) },
        space = space,
        selectedTextStyle = selectedTextStyle,
        unselectedTextStyle = unselectedTextStyle,
        extraRow = extraRow,
        isLooping = isLooping,
        overlayColor = overlayColor,
        itemToString = { it },
        longestText = items.maxBy { it.length },
    )
}