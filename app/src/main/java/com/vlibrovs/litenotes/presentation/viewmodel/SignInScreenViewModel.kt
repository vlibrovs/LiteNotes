package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.litenotes.domain.model.user.User
import com.vlibrovs.litenotes.domain.usecase.user.GetCurrentUserUseCase
import com.vlibrovs.litenotes.domain.usecase.user.SignInUserUseCase
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.launch

class SignInScreenViewModel(
    private val signInUser: SignInUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

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
                    is Resource.Loading -> _isLoading.value = true
                    is Resource.Success -> {
                        _isLoading.value = false
                        onFinish()
                    }
                    else -> {
                        _isLoading.value = false
                    }
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