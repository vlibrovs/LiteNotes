package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme

@Composable
fun FloatingAddButton(
    onClick: () -> Unit
) {
    LiteNotesTheme {
        FloatingActionButton(
            modifier = Modifier.size(60.dp),
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
        }
    }
}

@Composable
fun FloatingEditButton(
    onClick: () -> Unit
) {
    LiteNotesTheme {
        FloatingActionButton(
            modifier = Modifier.size(60.dp),
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Add note")
        }
    }
}