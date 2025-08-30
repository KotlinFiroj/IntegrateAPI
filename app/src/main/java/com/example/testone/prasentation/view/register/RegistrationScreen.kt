package com.example.testone.prasentation.view.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel(), innerPadding: PaddingValues) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            // value = viewModel.isFormValid().toString(), // just for demo
            value = "", // just for demo
            onValueChange = viewModel::onUsernameChanged,
            label = { Text("Username") },
        )
        OutlinedTextField(
            value = "",
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = "",
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Password") },
        )

        Button(
            onClick = viewModel::onSaveClicked,
            // enabled = viewModel.isFormValid() && state !is RegisterState.Loading,
            // enabled = viewModel.isFormValid() && state !is RegisterState.Loading,
        ) {
            Text("Save")
        }

        TextButton(onClick = { viewModel.onForgotPasswordClicked() }) {
            Text("Forgot Password?")
        }

        when (state) {
            is RegisterState.Loading -> Text("Loading...")
            is RegisterState.Success ->
                Text((state as RegisterState.Success).message.message)
            is RegisterState.Error ->
                Text((state as RegisterState.Error).error)
            RegisterState.Idle -> {} // do nothing
        }
    }
}
