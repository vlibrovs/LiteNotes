package com.vlibrovs.litenotes.data.repository

import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class FirebaseNoteRepository : NoteRepository {
    override suspend fun getAllNotes(user: User): Flow<List<Note>> {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(user: User, note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(user: User, note: Note) {
        TODO("Not yet implemented")
    }
}