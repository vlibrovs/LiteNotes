package com.vlibrovs.litenotes.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.UserRepository
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.flow

class FirebaseUserRepository(private val auth: FirebaseAuth) : UserRepository {
    override suspend fun getCurrentUser(): User? {
        val firebaseUser = auth.currentUser ?: return null
        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email!!
        )
    }

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun signUp(email: String, password: String) {
        Log.d("Auth", "Repository fun called")
        auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun signOut() {
        auth.signOut()
    }
}