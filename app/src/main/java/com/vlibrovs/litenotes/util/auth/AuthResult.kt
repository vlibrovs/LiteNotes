package com.vlibrovs.litenotes.util.auth

sealed class AuthResult {
    object Success : AuthResult()
    object NoInternetConnection : AuthResult()
    object InvalidEmail : AuthResult()
    object InvalidPassword : AuthResult()
    object WeakPassword : AuthResult()
    object EmailIsRegistered : AuthResult()
    object WrongPassword : AuthResult()
    object NoSuchUser : AuthResult()
    object UnknownError : AuthResult()
}
