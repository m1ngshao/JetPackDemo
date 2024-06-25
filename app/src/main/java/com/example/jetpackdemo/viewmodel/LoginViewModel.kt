package com.example.jetpackdemo.viewmodel

import androidx.lifecycle.ViewModel
import android.util.Patterns


class LoginViewModel : ViewModel() {

    private val phoneRegex = Regex("^(13[0-9]|14[5-9]|15[0-3,5-9]|166|17[0-8]|18[0-9]|19[8,9])\\d{8}\$")
    private val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{8,}\$")

    // A placeholder username validation check
    fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else if (username.length == 11) {
            phoneRegex.matches(username)
        } else {
            false
        }
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        return if (password.isNotEmpty()) {
            passwordRegex.matches(password)
        } else {
            false
        }
    }
}