package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vlibrovs.litenotes.domain.model.note.Note

class EditNoteScreenViewModel : ViewModel() {

    var note: Note? = null
        set(value) {
            field = value
            _titleState.value = value?.title ?: ""
            _contentState.value = value?.content ?: ""
        }

    private val _titleState = mutableStateOf("")
    val titleState: State<String> = _titleState

    private val _contentState = mutableStateOf("")
    val contentState: State<String> = _contentState

    val onTitleValueChange = { newValue: String ->
        _titleState.value = newValue
    }

    val onContentValueChange = { newValue: String ->
        _contentState.value = newValue
    }

    fun save(onFinish: () -> Unit) {
        // TODO
    }

}