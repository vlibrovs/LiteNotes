package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme

@Composable
fun RadioButton(
    modifier: Modifier = Modifier.size(16.dp),
    isSelected: Boolean
) {
    LiteNotesTheme {
        Box(
            modifier = modifier
                .border(
                    width = 2.dp,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary
                )
                .radioSelector(isSelected)
        )
    }
}

@Composable
private fun Modifier.radioSelector(isSelected: Boolean): Modifier {
    return if (isSelected) this
        .padding(4.dp)
        .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape) else this
}