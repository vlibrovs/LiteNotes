package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    LiteNotesTheme {
        IconButton(onClick = onClick) {
            Icon(
                modifier = Modifier
                    .width(21.dp)
                    .height(37.dp),
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}