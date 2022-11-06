package com.vlibrovs.litenotes.presentation.theme.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.vlibrovs.litenotes.R

val FontFamily.Companion.Lobster
    get() = FontFamily(
        Font(
            resId = R.font.lobster_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal
        )
    )
