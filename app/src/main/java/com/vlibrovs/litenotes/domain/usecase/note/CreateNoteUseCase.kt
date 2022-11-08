package com.vlibrovs.litenotes.domain.usecase.note

import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.Repository
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.flow

class CreateNoteUseCase(private val repository: Repository) {

    suspend operator fun invoke(
        user: User,
        note: Note
    ) = flow {
        emit(Resource.Loading())
        try {
            repository.addNote(user, note)
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(error = e))
        }
    }

}