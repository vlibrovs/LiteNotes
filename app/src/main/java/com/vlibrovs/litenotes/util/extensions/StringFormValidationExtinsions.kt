package com.vlibrovs.litenotes.util.extensions

import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    val pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    )
    return pattern.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    val lowercaseLetters = count { char -> char.isLowerCase() }
    val uppercaseLetters = count { char -> char.isUpperCase() }
    val digits = count { char -> char.isDigit() }
    val specialSymbols = count { char -> "#.,_-&()".contains(char) }
    return lowercaseLetters + uppercaseLetters + digits + specialSymbols == length
}

fun String.isStrongPassword(): Boolean {
    val lowercaseLetters = count { char -> char.isLowerCase() }
    val uppercaseLetters = count { char -> char.isUpperCase() }
    val digits = count { char -> char.isDigit() }
    val specialSymbols = count { char -> "#.,_-&()".contains(char) }
    return lowercaseLetters > 0 && uppercaseLetters > 0 && digits > 0 && specialSymbols > 0 && length >= 8
}