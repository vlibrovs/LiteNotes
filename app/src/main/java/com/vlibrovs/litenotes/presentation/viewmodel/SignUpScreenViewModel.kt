package com.vlibrovs.litenotes.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vlibrovs.litenotes.domain.usecase.user.SignUpUserUseCase
import com.vlibrovs.litenotes.util.resource.Resource
import kotlinx.coroutines.launch

class SignUpScreenViewModel(private val signUpUser: SignUpUserUseCase) : ViewModel() {

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
        Log.d("Auth", "ViewModel fun called called")
        viewModelScope.launch {
            signUpUser(emailState, passwordState, confirmPasswordState).collect { resource ->
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

}
