package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vlibrovs.litenotes.domain.model.note.Note

class EditNoteScreenViewModel : ViewModel() {

    var note: Note? = null

    private val _titleState = mutableStateOf(note?.title ?: "")
    val titleState = _titleState

    private val _contentState = mutableStateOf(note?.title ?: "")
    val contentState = _contentState

    val onTitleValueChange = { newValue: String ->
        _titleState.value = newValue
    }

    val onContentValueChange = { newValue: String ->
        _contentState.value = newValue
    }

    fun save(onSuccess: () -> Unit) {
        // TODO
    }

    fun cancel() {
        // TODO
    }

}