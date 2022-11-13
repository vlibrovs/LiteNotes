package com.vlibrovs.litenotes.domain.usecase.user

import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.UserRepository

class GetCurrentUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(): User? = userRepository.getCurrentUser()

}