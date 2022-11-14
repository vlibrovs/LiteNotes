package com.vlibrovs.litenotes.domain.usecase.user

import com.vlibrovs.litenotes.domain.repository.UserRepository
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.extensions.isStrongPassword
import com.vlibrovs.litenotes.util.extensions.isValidEmail
import com.vlibrovs.litenotes.util.extensions.isValidPassword
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SignUpUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(email: String, password: String, confirmPassword: String) =
        flow<Resource<AuthResult>> {
            emit(Resource.Loading())
            if (email.isEmpty()) return@flow emit(Resource.Error(data = AuthResult.EmptyEmail))
            if (!email.isValidEmail()) return@flow emit(Resource.Error(data = AuthResult.InvalidEmail))

            if (password.isEmpty()) return@flow emit(Resource.Error(data = AuthResult.EmptyPassword))
            if (!password.isValidPassword()) return@flow emit(Resource.Error(data = AuthResult.InvalidPassword))
            if (!password.isStrongPassword()) return@flow emit(Resource.Error(data = AuthResult.WeakPassword))

            if (confirmPassword.isEmpty()) return@flow emit(Resource.Error(data = AuthResult.EmptyConfirmPassword))
            if (password != confirmPassword) return@flow emit(Resource.Error(data = AuthResult.PasswordIsNotConfirmed))

            try {
                repository.signUp(email = email, password = password)
                emit(Resource.Success(AuthResult.Success))
            } catch (e: IOException) {
                emit(Resource.Error(data = AuthResult.NoInternetConnection))
            } catch (e: HttpException) {
                emit(Resource.Error(data = AuthResult.ServerError))
            } catch (e: Exception) {
                emit(Resource.Error(data = AuthResult.UnknownError, error = e))
            }
        }

}