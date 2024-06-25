package com.example.jetpackdemo.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.example.jetpackdemo.R
import com.example.jetpackdemo.databinding.ActivityLoginBinding
import com.example.jetpackdemo.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        var usernameIsValid = false
        var pwdIsValid = false

        loginViewModel = ViewModelProvider(this)
            .get(LoginViewModel::class.java)

        login.setOnClickListener {
            if (loginViewModel.isUserNameValid(username.text.toString().trim())) {
                usernameIsValid = true
            }
            if (loginViewModel.isPasswordValid(password.text.toString().trim())) {
                pwdIsValid = true
            }
            if (usernameIsValid && pwdIsValid) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "请检查用户名和密码格式是否正确", Toast.LENGTH_SHORT).show()
            }
        }
    }

}