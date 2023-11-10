package com.example.pmdm_login

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pmdm_login.ui.theme.PMDM_LoginTheme

const val PASSWORD_LENGTH = 6

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
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
                    var email by remember {
                        mutableStateOf(TextFieldValue(""))
                    }

                    var password by remember {
                        mutableStateOf(TextFieldValue(""))
                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        item {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = "Logo_IES",
                                Modifier
                                    .clip(CircleShape)
                                    .size(100.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        item {
                            TextField(
                                value = email,
                                onValueChange = {
                                    email = it
                                    isDataAccepted(email, password)
                                },
                                label = { Text(text = "Email") }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        item {
                            var passwordVisible by rememberSaveable {
                                mutableStateOf(false)
                            }

                            TextField(
                                value = password,
                                onValueChange = {
                                    password = it
                                    isDataAccepted(email, password)
                                },
                                label = { Text(text = "Password") },
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(
                                            imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                            contentDescription = "Visibility"
                                        )
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        
                        item {
                            Button(
                                onClick = {
                                    Toast.makeText(context, "Log in successful", Toast.LENGTH_SHORT).show()
                                    email = TextFieldValue("")
                                    password = TextFieldValue("")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 50.dp, end = 50.dp),
                                enabled = isDataAccepted(email, password)
                            ) {
                                Text(text = "Log in", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}


fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    /*val regexPattern = Regex("^[A-Za-z0-9+_.-]+@(.+)\$") // Expresión regular simple para validar un correo electrónico
    return regexPattern.matches(email)*/
}

fun isDataAccepted(email: TextFieldValue, password: TextFieldValue): Boolean {
    return if (isEmailValid(email.text) && password.text.length >= PASSWORD_LENGTH) true else false
}