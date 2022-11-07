package com.vlibrovs.litenotes.presentation.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.presentation.compose.widgets.Button
import com.vlibrovs.litenotes.presentation.compose.widgets.ButtonStyle
import com.vlibrovs.litenotes.presentation.compose.widgets.TextField
import com.vlibrovs.litenotes.presentation.compose.widgets.TextFieldType
import com.vlibrovs.litenotes.presentation.viewmodel.EditNoteScreenViewModel

@Composable
fun EditNoteScreen(
    viewModel: EditNoteScreenViewModel
) {
    LiteNotesTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = viewModel.titleState.value,
                    onValueChange = viewModel.onTitleValueChange,
                    label = stringResource(id = R.string.title),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 250.dp, max = 400.dp),
                    value = viewModel.contentState.value,
                    onValueChange = viewModel.onContentValueChange,
                    label = stringResource(id = R.string.content),
                    type = TextFieldType.TextArea
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    style = ButtonStyle.Filled,
                    onClick = { viewModel.save() },
                    text = stringResource(id = R.string.save)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    style = ButtonStyle.Outlined,
                    onClick = { viewModel.cancel() },
                    text = stringResource(id = R.string.cancel)
                )
            }
        }
    }
}