package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.usecase.user.GetCurrentUserUseCase
import com.vlibrovs.litenotes.domain.usecase.user.SignInUserUseCase
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.launch

class SignInScreenViewModel(
    private val signInUser: SignInUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    private val _pageErrorState = mutableStateOf(false)
    val pageErrorState: State<Boolean> get() = _pageErrorState

    private val _pageErrorStringResourceState: MutableState<Int?> = mutableStateOf(null)
    val pageErrorStringResourceState: State<Int?> get() = _pageErrorStringResourceState

    private val _emailErrorState = mutableStateOf(false)
    val emailErrorState: State<Boolean> get() = _emailErrorState

    private val _emailErrorStringResourceState: MutableState<Int?> = mutableStateOf(null)
    val emailErrorStringResourceState: State<Int?> get() = _emailErrorStringResourceState

    private val _passwordErrorState = mutableStateOf(false)
    val passwordErrorState: State<Boolean> get() = _passwordErrorState

    private val _passwordErrorStringResourceState: MutableState<Int?> = mutableStateOf(null)
    val passwordErrorStringResourceState: State<Int?> get() = _passwordErrorStringResourceState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _currentUser = mutableStateOf<User?>(null)
    val currentUser: State<User?>
        get() = _currentUser

    private val _emailState = mutableStateOf("")
    val emailState: String
        get() = _emailState.value


    private val _passwordState = mutableStateOf("")
    val passwordState: String
        get() = _passwordState.value

    val onEmailValueChange = { newValue: String ->
        _emailState.value = newValue
    }

    val onPasswordValueChange = { newValue: String ->
        _passwordState.value = newValue
    }

    fun signIn(
        onFinish: () -> Unit
    ) {
        viewModelScope.launch {
            signInUser(emailState, passwordState).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                        _emailErrorState.value = false
                        _passwordErrorState.value = false
                    }
                    is Resource.Success -> {
                        _isLoading.value = false
                        _emailErrorState.value = false
                        _passwordErrorState.value = false
                        onFinish()
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        when (resource.data) {
                            is AuthResult.EmptyEmail -> {
                                _emailErrorStringResourceState.value =
                                    R.string.empty_email_error
                                _emailErrorState.value = true
                            }
                            is AuthResult.EmptyPassword -> {
                                _passwordErrorStringResourceState.value =
                                    R.string.empty_password_singin_error
                                _passwordErrorState.value = true
                            }
                            is AuthResult.InvalidEmail -> {
                                _emailErrorStringResourceState.value =
                                    R.string.invalid_email_error
                                _emailErrorState.value = true
                            }
                            is AuthResult.NoSuchUser -> {
                                _emailErrorState.value = true
                                _emailErrorStringResourceState.value = R.string.no_such_user_error
                            }
                            is AuthResult.WrongPassword -> {
                                _passwordErrorState.value = true
                                _passwordErrorStringResourceState.value =
                                    R.string.wrong_password_error
                            }
                            is AuthResult.NoInternetConnection -> {
                                _pageErrorState.value = true
                                _pageErrorStringResourceState.value =
                                    R.string.no_internet_connection_error
                            }
                            is AuthResult.ServerError -> {
                                _pageErrorState.value = true
                                _pageErrorStringResourceState.value = R.string.server_error
                            }
                            is AuthResult.WrongPasswordOrEmail -> {
                                _pageErrorState.value = true
                                _pageErrorStringResourceState.value = R.string.wrong_email_or_password
                            }
                            else -> _passwordErrorStringResourceState.value = R.string.unknown_error
                        }
                    }
                    else -> _isLoading.value = false
                }
            }
        }
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
        }
    }

    init {
        getCurrentUser()
    }

}