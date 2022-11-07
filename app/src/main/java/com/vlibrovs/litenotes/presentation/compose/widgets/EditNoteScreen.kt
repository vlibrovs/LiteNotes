package com.vlibrovs.litenotes.presentation.compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.R

@Preview
@Composable
fun EditNoteScreen(
    note: Note = Note(title = "", content = ""), onNoteSave: (note: Note) -> Unit = {}
) {
    var titleValue by remember {
        mutableStateOf(note.title)
    }
    var contentValue by remember {
        mutableStateOf(note.content)
    }
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
                    value = titleValue,
                    onValueChange = { titleValue = it },
                    label = stringResource(id = R.string.title),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 250.dp, max = 400.dp),
                    value = contentValue,
                    onValueChange = { contentValue = it },
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
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.save)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    style = ButtonStyle.Outlined,
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.cancel)
                )
            }
        }
    }
}