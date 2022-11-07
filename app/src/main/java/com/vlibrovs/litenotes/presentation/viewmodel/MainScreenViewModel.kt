package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User

class MainScreenViewModel : ViewModel() {

    private val allNotes = mutableListOf<Note>(
        Note(title = "Note1", content = LoremIpsum(20).values.toText()),
        Note(title = "Note2", content = LoremIpsum(8).values.toText()),
        Note(title = "Note3", content = LoremIpsum(12).values.toText()),
        Note(title = "Note4", content = LoremIpsum(15).values.toText()),
        Note(title = "Note5", content = LoremIpsum(10).values.toText()),
        Note(title = "Note6", content = LoremIpsum(13).values.toText()),
    )

    private val _displayingNotes = mutableListOf<Note>().apply { addAll(allNotes) }
    val displayingNotes: List<Note> get() = _displayingNotes

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery.value

    val onSearchQueryChange = { newValue: String ->
        _searchQuery.value = newValue
    }

    fun search() {
        _displayingNotes.clear()
        if (searchQuery.isEmpty()) {
            _displayingNotes.addAll(allNotes)
            return
        }
        for (note in allNotes) {
            if (note.title.lowercase().contains(searchQuery.lowercase()) && note.content.lowercase()
                    .contains(
                        searchQuery.lowercase()
                    )
            ) {
                _displayingNotes.add(note)
            }
        }
    }

    fun getNotes(user: User) {
        // TODO
    }

    private fun Sequence<String>.toText(): String {
        val sb = StringBuilder()
        for (string in this) {
            sb.append(string).append(' ')
        }
        return sb.trim().toString()
    }

}