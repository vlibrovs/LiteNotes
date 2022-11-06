package com.vlibrovs.litenotes.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.vlibrovs.litenotes.presentation.theme.fonts.Lobster
import com.vlibrovs.litenotes.presentation.theme.fonts.Poppins

val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 72.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Lobster,
        textAlign = TextAlign.Center
    ),
    displayMedium = TextStyle(
        fontSize = 48.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Lobster,
        textAlign = TextAlign.Center
    ),
    displaySmall = TextStyle(
        fontSize = 40.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Poppins,
        textAlign = TextAlign.Center
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Poppins,
        textAlign = TextAlign.Start
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Poppins,
        textAlign = TextAlign.Start
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Poppins,
        textAlign = TextAlign.Start
    ),
    labelLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Poppins,
        textAlign = TextAlign.Center
    )
)