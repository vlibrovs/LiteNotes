package com.vlibrovs.litenotes.domain.repository

import com.vlibrovs.litenotes.domain.model.note.Note
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.util.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getAllNotes(user: User): Flow<List<Note>>
    suspend fun addNote(user: User, note: Note)
    suspend fun deleteNote(user: User, note: Note)
}