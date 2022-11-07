package com.vlibrovs.litenotes.presentation.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.presentation.compose.widgets.*

@Composable
fun MainScreen(
    notes: List<Note>
) {
    var searchQuery by remember {
        mutableStateOf("")
    }
    var displayedNotes by remember {
        mutableStateOf(notes)
    }
    LiteNotesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 30.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.displayMedium,
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                modifier = Modifier.size(48.dp),
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = stringResource(id = R.string.sign_out),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                item {
                    TextField(value = searchQuery,
                        onValueChange = { searchQuery = it },
                        label = stringResource(
                            id = R.string.search
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        type = TextFieldType.Search,
                        onSearch = { /*TODO*/ })
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(.5f)
                                .padding(end = 15.dp)
                        ) {
                            for ((index, note) in displayedNotes.withIndex()
                                .filterIndexed { index, _ -> index % 2 == 0 }) {
                                NoteItem(
                                    note = note,
                                    onClick = { /*TODO*/ },
                                    style = when (index % 3) {
                                        0 -> NoteItemStyle.Primary
                                        1 -> NoteItemStyle.Secondary
                                        else -> NoteItemStyle.Tertiary
                                    },
                                    onLongClick = {
                                        // TODO
                                    },
                                    outerPadding = PaddingValues(bottom = 30.dp)
                                )
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp)
                        ) {
                            for ((index, note) in displayedNotes.withIndex()
                                .filterIndexed { index, _ -> index % 2 == 1 }) {
                                NoteItem(
                                    note = note,
                                    onClick = { /*TODO*/ },
                                    style = when (index % 3) {
                                        0 -> NoteItemStyle.Primary
                                        1 -> NoteItemStyle.Secondary
                                        else -> NoteItemStyle.Tertiary
                                    },
                                    onLongClick = {
                                        // TODO
                                    },
                                    outerPadding = PaddingValues(bottom = 30.dp)
                                )
                            }
                        }
                    }
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingAddButton {
                    // TODO
                }
            }
        }
    }
}