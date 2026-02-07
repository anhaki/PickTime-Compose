package com.anhaki.picktime.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anhaki.picktime.utils.PickTimeTextStyle

/**
 * A composable wheel for picking numeric values, rendering numbers with two-digit formatting.
 *
 * @param modifier Modifier applied to the wheel layout.
 * @param items List of integers to be displayed.
 * @param selectedItem Currently selected item (by value, not index).
 * @param onItemSelected Callback when an item is selected (returns the value).
 * @param space Vertical spacing between wheel items.
 * @param selectedTextStyle Text style for selected item.
 * @param unselectedTextStyle Text style for unselected items.
 * @param extraRow Number of additional rows (empty or transparent) above and below the list to create a center focus effect.
 * @param isLooping Whether the wheel should loop infinitely.
 * @param overlayColor Color of the top and bottom gradient overlays.
 */

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
        longestText = "00"
    )
}
