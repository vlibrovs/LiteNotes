package com.vlibrovs.litenotes.domain.usecase.user

import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseNetworkException
import com.vlibrovs.litenotes.domain.repository.UserRepository
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SignOutUserUseCase(private val repository: UserRepository) {

    suspend operator fun invoke() = flow<Resource<AuthResult>> {
        emit(Resource.Loading())
        try {
            repository.signOut()
            emit(Resource.Success(AuthResult.Success))
        } catch (e: IOException) {
            emit(Resource.Error(data = AuthResult.NoInternetConnection))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(data = AuthResult.NoInternetConnection))
        } catch (e: FirebaseApiNotAvailableException) {
            emit(Resource.Error(data = AuthResult.ServerError))
        } catch (e: HttpException) {
            emit(Resource.Error(data = AuthResult.ServerError))
        } catch (e: Exception) {
            emit(Resource.Error(data = AuthResult.UnknownError, error = e))
        }
    }

}