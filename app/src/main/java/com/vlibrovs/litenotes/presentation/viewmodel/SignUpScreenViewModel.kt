package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseNetworkException
import com.vlibrovs.litenotes.R
import com.vlibrovs.litenotes.domain.usecase.user.SignUpUserUseCase
import com.vlibrovs.litenotes.util.auth.AuthResult
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.launch

class SignUpScreenViewModel(private val signUpUser: SignUpUserUseCase) : ViewModel() {

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

    private val _confirmPasswordErrorState = mutableStateOf(false)
    val confirmPasswordErrorState: State<Boolean> get() = _confirmPasswordErrorState

    private val _confirmPasswordStringResourceState: MutableState<Int?> = mutableStateOf(null)
    val confirmPasswordStringResourceState: State<Int?> get() = _confirmPasswordStringResourceState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _emailState = mutableStateOf("")
    val emailState: String
        get() = _emailState.value

    private val _passwordState = mutableStateOf("")
    val passwordState: String
        get() = _passwordState.value

    private val _confirmPasswordState = mutableStateOf("")
    val confirmPasswordState: String
        get() = _confirmPasswordState.value

    val onEmailValueChange = { newValue: String ->
        _emailState.value = newValue
    }

    val onPasswordValueChange = { newValue: String ->
        _passwordState.value = newValue
    }

    val onConfirmPasswordValueChange = { newValue: String ->
        _confirmPasswordState.value = newValue
    }

    fun signUp(
        onFinish: () -> Unit
    ) {
        viewModelScope.launch {
            val flow = signUpUser(emailState, passwordState, confirmPasswordState)
            flow.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _isLoading.value = true
                        _emailErrorState.value = false
                        _passwordErrorState.value = false
                        _confirmPasswordErrorState.value = false
                        _pageErrorState.value = false
                    }
                    is Resource.Success -> {
                        _isLoading.value = false
                        _emailErrorState.value = false
                        _passwordErrorState.value = false
                        _confirmPasswordErrorState.value = false
                        _pageErrorState.value = false
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
                            is AuthResult.InvalidEmail -> {
                                _emailErrorStringResourceState.value =
                                    R.string.invalid_email_error
                                _emailErrorState.value = true
                            }
                            is AuthResult.EmailIsRegistered -> {
                                _emailErrorStringResourceState.value =
                                    R.string.email_is_registered_error
                                _emailErrorState.value = true
                            }
                            is AuthResult.EmptyPassword -> {
                                _passwordErrorStringResourceState.value =
                                    R.string.empty_password_error
                                _passwordErrorState.value = true
                            }
                            is AuthResult.InvalidPassword -> {
                                _passwordErrorStringResourceState.value =
                                    R.string.invalid_password_error
                                _passwordErrorState.value = true
                            }
                            is AuthResult.WeakPassword -> {
                                _passwordErrorStringResourceState.value =
                                    R.string.weak_password_error
                                _passwordErrorState.value = true
                            }
                            is AuthResult.EmptyConfirmPassword -> {
                                _confirmPasswordStringResourceState.value =
                                    R.string.empty_confirm_password_error
                                _confirmPasswordErrorState.value = true
                            }
                            is AuthResult.PasswordIsNotConfirmed -> {
                                _confirmPasswordStringResourceState.value =
                                    R.string.passwords_dont_match_error
                                _confirmPasswordErrorState.value = true
                            }
                            is AuthResult.NoInternetConnection -> {
                                _pageErrorState.value = true
                                _pageErrorStringResourceState.value = R.string.no_internet_connection_error
                            }
                            is AuthResult.ServerError -> {
                                _pageErrorState.value = true
                                _pageErrorStringResourceState.value = R.string.server_error
                            }
                            else -> Unit
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

}
