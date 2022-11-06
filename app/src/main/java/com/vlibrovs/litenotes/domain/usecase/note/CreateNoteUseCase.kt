package com.vlibrovs.litenotes.domain.usecase.note

import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.Repository

class CreateNoteUseCase(private val repository: Repository) {

    suspend operator fun invoke(
        user: User,
        note: Note
    ) {
        // TODO
    }

}