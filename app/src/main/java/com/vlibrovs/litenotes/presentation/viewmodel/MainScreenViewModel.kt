package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.usecase.user.SignOutUserUseCase
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.launch

class MainScreenViewModel(private val signOutUser: SignOutUserUseCase) : ViewModel() {

    private val allNotes = mutableListOf<Note>()

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

    fun signOut(
        onFinish: () -> Unit
    ) {
        viewModelScope.launch {
            signOutUser().collect { resource ->
                if (resource is Resource.Success) {
                    onFinish()
                }
            }
        }
    }

    private fun Sequence<String>.toText(): String {
        val sb = StringBuilder()
        for (string in this) {
            sb.append(string).append(' ')
        }
        return sb.trim().toString()
    }

}