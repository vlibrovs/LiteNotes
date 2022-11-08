package com.vlibrovs.litenotes.domain.usecase.user

import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.repository.Repository
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SignOutUserUseCase(private val repository: Repository) {

    suspend operator fun invoke(user: User) = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        try {
            repository.signOutUser(user)
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