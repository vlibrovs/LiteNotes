package com.vlibrovs.litenotes.domain.repository

import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getCurrentUser(): User?
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String)
    suspend fun signOut()
}