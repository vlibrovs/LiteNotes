package com.vlibrovs.litenotes.domain.model.user

import com.vlibrovs.litenotes.domain.model.note.Note

data class User(
    val email: String,
    val password: String,
    var notes: List<Note>
)
