package com.vlibrovs.litenotes.domain.usecase.user

import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.Repository

class SignOutUserUseCase(private val repository: Repository) {

    suspend operator fun invoke(user: User) {
        // TODO
    }

}