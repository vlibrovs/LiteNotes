package com.vlibrovs.litenotes.domain.usecase.note

import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.Repository
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.flow

class EditNoteUseCase(private val repository: Repository) {

    suspend operator fun invoke(
        user: User,
        oldContent: Note,
        newContent: Note
    ) = flow {
        emit(Resource.Loading())
        try {
            if (oldContent == newContent) {
                emit(Resource.Success(Unit))
                return@flow
            }
            repository.addNote(user, newContent)
            repository.deleteNote(user, oldContent)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(error = e))
        }
    }
}