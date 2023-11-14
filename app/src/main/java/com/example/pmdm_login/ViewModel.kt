package com.example.pmdm_login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginDataViewModel: ViewModel() {
    private var _email = MutableLiveData("")
    var email: LiveData<String> = _email

    private var _password = MutableLiveData("")
    var password: LiveData<String> = _password

    fun validEmail(): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(this.email.toString()).matches()
    }

    fun validPassword(): Boolean {
        return if (password.toString().length >= PASSWORD_LENGTH) true else false
    }

    fun setValue() {

    }
}
