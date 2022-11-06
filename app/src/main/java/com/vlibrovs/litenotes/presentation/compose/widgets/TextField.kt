package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme

@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    type: TextFieldType = TextFieldType.Default,
    onSearch: (String) -> Unit = {}
) {
    val textFieldColors = MaterialTheme.colorScheme.run {
        TextFieldDefaults.outlinedTextFieldColors(
            textColor = onBackground,
            disabledTextColor = outline,
            cursorColor = primary,
            errorCursorColor = error,
            focusedLabelColor = primary,
            unfocusedLabelColor = outline,
            focusedBorderColor = primary,
            unfocusedBorderColor = outline
        )
    }
    LiteNotesTheme {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            label = {
                Text(text = label)
            },
            visualTransformation = if (type == TextFieldType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = when (type) {
                TextFieldType.Email -> KeyboardOptions(keyboardType = KeyboardType.Email)
                TextFieldType.Search -> KeyboardOptions(imeAction = ImeAction.Search)
                else -> KeyboardOptions.Default
            },
            keyboardActions = if (type == TextFieldType.Search) KeyboardActions(onSearch = {
                onSearch(value)
            }) else KeyboardActions.Default,
            singleLine = true,
            colors = textFieldColors,
            trailingIcon = if (type != TextFieldType.Search) null else {
                {
                    IconButton(onClick = {
                        onSearch(value)
                    }) {
                        Icon(
                            painter = rememberVectorPainter(image = Icons.Outlined.Search),
                            contentDescription = "Search"
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyLarge,
        )
    }
}

sealed class TextFieldType {
    object Default : TextFieldType()
    object Password : TextFieldType()
    object Search : TextFieldType()
    object Email : TextFieldType()
}