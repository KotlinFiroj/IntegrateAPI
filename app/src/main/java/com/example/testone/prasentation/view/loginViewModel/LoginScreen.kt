package com.example.testone.prasentation.view.loginViewModel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testone.app.ui.theme.loginBG
import com.example.testone.prasentation.viewModel.LoginViewModel

@Composable
fun LoadLogin(onHome: () -> Unit, viewModel: LoginViewModel = hiltViewModel()) {
    val state by viewModel.loginResponse.collectAsState()
    val loginForm by viewModel.loginForm.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ConstraintLayout(modifier = Modifier.padding(8.dp)) {
            val (card, userName, password, loginButton) = createRefs()
            Box(
                modifier = Modifier.background(color = loginBG)
                    .constrainAs(card) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            ) {
            }

            /*OutlinedTextField(
                // value = viewModel.isFormValid().toString(), // just for demo
                value = loginForm.name, // just for demo
                onValueChange = viewModel::onUsernameChanged,
                label = { Text("Username") },
            )*/

            OutlinedTextField(
                modifier = Modifier.padding(8.dp)
                    .constrainAs(userName) {
                        top.linkTo(card.top, 8.dp)
                        start.linkTo(card.start, 8.dp)
                    },
                value = loginForm.username,
                onValueChange = viewModel::onUserNameChange,
                label = { Text("UserName") },

            )

            OutlinedTextField(
                modifier = Modifier.padding(8.dp)
                    .constrainAs(password) {
                        start.linkTo(userName.start)
                        top.linkTo(userName.bottom, 8.dp)
                    },
                value = loginForm.password,
                onValueChange = viewModel::onPasswordChange,
                label = { Text("Password") },
            )

            Button(
                modifier = Modifier.padding(8.dp)
                    .constrainAs(loginButton) {
                        start.linkTo(userName.start)
                        top.linkTo(password.bottom, 8.dp)
                    },
                onClick = { viewModel.login() },
                enabled = viewModel.isValidForm() && state !is LoginState.Loading,
            ) { Text(text = "Login") }

            when (state) {
                is LoginState.Error -> {
                }
                is LoginState.Idle -> {
                }
                is LoginState.Loading -> {
                }
                is LoginState.Success<*> -> {
                    onHome()
                }
            }
        }
    }
}
