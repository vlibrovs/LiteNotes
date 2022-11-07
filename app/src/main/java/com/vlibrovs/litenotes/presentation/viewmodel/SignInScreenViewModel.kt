package com.vlibrovs.litenotes.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignInScreenViewModel : ViewModel() {

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

}