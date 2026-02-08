package com.anhaki.picktime.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.anhaki.picktime.utils.PickTimeTextStyle

/**
 * A composable wheel for picking string values (labels, names, etc.).
 *
 * @param modifier Modifier applied to the wheel layout.
 * @param items List of string items to be displayed.
 * @param selectedItem Currently selected item (by 1-based index).
 * @param onItemSelected Callback when an item is selected (returns the index).
 * @param space Vertical spacing between wheel items.
 * @param selectedTextStyle Text style for selected item.
 * @param unselectedTextStyle Text style for unselected items.
 * @param extraRow Number of additional rows (empty or transparent) above and below the list to create a center focus effect.
 * @param isLooping Whether the wheel should loop infinitely.
 * @param overlayColor Color of the top and bottom gradient overlays.
 */

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
    key(items) {
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
}