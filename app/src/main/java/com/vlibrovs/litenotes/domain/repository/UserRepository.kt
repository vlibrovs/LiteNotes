package com.vlibrovs.litenotes.domain.repository

import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.util.auth.AuthResult

interface UserRepository {
    suspend fun signInUser(user: User): AuthResult
    suspend fun signUpUser(user: User): AuthResult
    suspend fun signOutUser(user: User)
    suspend fun getUserByEmail(email: String): User?
}