package com.vlibrovs.litenotes.domain.usecase.user

import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.NoteRepository
import com.vlibrovs.litenotes.domain.repository.UserRepository
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.extensions.isStrongPassword
import com.vlibrovs.litenotes.util.extensions.isValidEmail
import com.vlibrovs.litenotes.util.extensions.isValidPassword
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SignUpUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(email: String, password: String, confirmPassword: String) =
        flow<Resource<AuthResult>> {
            emit(Resource.Loading())
            if (email.isEmpty()) {
                emit(Resource.Error(data = AuthResult.EmptyEmail))
                return@flow
            }
            if (password.isEmpty()) {
                emit(Resource.Error(data = AuthResult.EmptyPassword))
                return@flow
            }
            if (confirmPassword.isEmpty()) {
                emit(Resource.Error(data = AuthResult.EmptyConfirmPassword))
                return@flow
            }
            if (!email.isValidEmail()) {
                emit(Resource.Error(data = AuthResult.InvalidEmail))
                return@flow
            }
            if (!password.isValidPassword()) {
                emit(Resource.Error(data = AuthResult.InvalidPassword))
                return@flow
            }
            if (!password.isStrongPassword()) {
                emit(Resource.Error(data = AuthResult.WeakPassword))
                return@flow
            }
            if (password != confirmPassword) {
                emit(Resource.Error(data = AuthResult.PasswordIsNotConfirmed))
                return@flow
            }
            try {
                if (repository.getUserByEmail(email) != null) {
                    emit(Resource.Error(data = AuthResult.EmailIsRegistered))
                    return@flow
                }
                repository.signUpUser(User(email = email, password = password))
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