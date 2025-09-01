package com.example.testone.prasentation.view.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testone.prasentation.viewModel.RegistrationViewModel

@Composable
fun RegistrationScreen(onNavLogin: () -> Unit, viewModel: RegistrationViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val form by viewModel.currentForm.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            // value = viewModel.isFormValid().toString(), // just for demo
            value = form.name, // just for demo
            onValueChange = viewModel::onUsernameChanged,
            label = { Text("Username") },
        )
        OutlinedTextField(
            value = form.email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = form.password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Password") },
        )

        Button(
            onClick = viewModel::onSaveClicked,
            enabled = viewModel.isFormValid() && state !is RegisterState.Loading,
        ) {
            Text("Save")
        }

        TextButton(onClick = {
            onNavLogin()
        }) {
            Text("Login")
        }

        TextButton(onClick = { viewModel.onForgotPasswordClicked() }) {
            Text("Forgot Password?")
        }

        when (state) {
            is RegisterState.Loading -> Text("Loading...")
            is RegisterState.Success -> {
                Text((state as RegisterState.Success).message.message)
                onNavLogin()
            }
            is RegisterState.Error ->
                Text((state as RegisterState.Error).error)
            RegisterState.Idle -> {} // do nothing
            RegisterState.Login -> {
                Text("Login...")
                onNavLogin()
            }
        }

        // React to registerState changes and trigger navigation
        /*LaunchedEffect(registerState) {
            when (registerState) {
         */
        /*RegisterState.Login -> navController.navigate(NavDestinations.Login.route)
            else -> navController.navigate(NavDestinations.Register.route)*/
        /*
            RegisterState.Login -> NavDestinations.Login.route
            else -> NavDestinations.Register.route
        }
    }*/
        // LaunchedEffect(registerState) {
            /*when (registerState) {
                RegisterState.Login -> navController.navigate(NavDestinations.Login.route)
                //RegisterState.Success -> navController.navigate(NavDestinations.UserList.route)
                else -> {} // No navigation for other states
            }*/
        // }
    }
}
