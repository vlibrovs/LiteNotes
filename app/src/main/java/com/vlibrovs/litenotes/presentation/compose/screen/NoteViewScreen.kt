package com.vlibrovs.litenotes.presentation.compose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.presentation.compose.widgets.BackButton
import com.vlibrovs.litenotes.presentation.compose.widgets.FloatingEditButton
import com.vlibrovs.litenotes.presentation.theme.LiteNotesTheme
import com.vlibrovs.litenotes.presentation.viewmodel.EditNoteScreenViewModel
import com.vlibrovs.litenotes.util.screen.Screen

@Composable
fun NoteViewScreen(
    note: Note,
    navController: NavController,
    viewModel: EditNoteScreenViewModel
) {
    LiteNotesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background)
                    .padding(horizontal = 30.dp)
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        SelectionContainer {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = note.title,
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.displaySmall,
                                textAlign = TextAlign.Center
                            )
                        }
                        BackButton {
                            navController.popBackStack()
                        }
                    }
                }

                item {
                    SelectionContainer {
                        Text(
                            text = note.content,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 30.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                FloatingEditButton {
                    viewModel.note = note
                    navController.navigate(Screen.EditNoteScreen.route)
                }
            }
        }
    }
}