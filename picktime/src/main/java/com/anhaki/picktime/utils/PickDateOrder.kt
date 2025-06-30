package com.anhaki.picktime.utils

enum class PickDateField {
    DAY, MONTH, YEAR
}

enum class PickDateOrder(val order: List<PickDateField>) {
    DMY(listOf(PickDateField.DAY, PickDateField.MONTH, PickDateField.YEAR)),
    MDY(listOf(PickDateField.MONTH, PickDateField.DAY, PickDateField.YEAR)),
    YMD(listOf(PickDateField.YEAR, PickDateField.MONTH, PickDateField.DAY)),
    YDM(listOf(PickDateField.YEAR, PickDateField.DAY, PickDateField.MONTH)),
    MYD(listOf(PickDateField.MONTH, PickDateField.YEAR, PickDateField.DAY)),
    DYM(listOf(PickDateField.DAY, PickDateField.YEAR, PickDateField.MONTH)),
}
