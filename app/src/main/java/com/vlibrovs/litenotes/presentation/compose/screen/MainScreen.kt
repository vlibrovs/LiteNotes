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
import androidx.navigation.NavController
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.presentation.compose.widgets.*
import com.vlibrovs.litenotes.presentation.viewmodel.MainScreenViewModel
import com.vlibrovs.litenotes.util.screen.Screen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    navController: NavController
) {
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
                        IconButton(onClick = {
                            viewModel.signOut {
                                navController.navigate(Screen.StartScreen.route)
                            }
                        }) {
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
                    TextField(value = viewModel.searchQuery,
                        onValueChange = viewModel.onSearchQueryChange,
                        label = stringResource(id = R.string.search),
                        modifier = Modifier.fillMaxWidth(),
                        type = TextFieldType.Search,
                        onSearch = { viewModel.search() })
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
                            for ((index, note) in viewModel.displayingNotes.withIndex()
                                .filterIndexed { index, _ -> index % 2 == 0 }) {
                                NoteItem(
                                    note = note,
                                    onClick = {
                                        navController.navigate(Screen.NoteViewScreen.routeWith(note))
                                    },
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
                            for ((index, note) in viewModel.displayingNotes.withIndex()
                                .filterIndexed { index, _ -> index % 2 == 1 }) {
                                NoteItem(
                                    note = note,
                                    onClick = {
                                        navController.navigate(Screen.NoteViewScreen.routeWith(note))
                                    },
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