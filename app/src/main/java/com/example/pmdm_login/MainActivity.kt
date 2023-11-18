package com.example.pmdm_login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.pmdm_login.ui.theme.PMDM_LoginTheme
import com.example.pmdm_login.view.LoginScreen
import com.example.pmdm_login.viewmodel.LoginDataViewModel

const val PASSWORD_LENGTH = 6

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PMDM_LoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var context = LocalContext.current

                    LoginScreen(loginData = LoginDataViewModel(), context)
                }
            }
        }
    }
}