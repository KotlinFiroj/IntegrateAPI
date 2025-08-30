package com.example.testone.prasentation.view.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testone.prasentation.RegistrationUiState
import com.example.testone.prasentation.viewModel.RegistrationViewModel

@Composable
fun RegistrationScreen(viewModel: RegistrationViewModel = hiltViewModel(), innerPadding: PaddingValues) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(innerPadding)) {
        OutlinedTextField(
            value = viewModel.isFormValid().toString(), // just for demo
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
            enabled = viewModel.isFormValid() && state !is RegistrationUiState.Loading,
        ) {
            Text("Save")
        }

        TextButton(onClick = { viewModel.onForgotPasswordClicked() }) {
            Text("Forgot Password?")
        }

        when (state) {
            is RegistrationUiState.Loading -> Text("Loading...")
            is RegistrationUiState.RegistrationSuccess ->
                Text((state as RegistrationUiState.RegistrationSuccess).message)
            is RegistrationUiState.RegistrationError ->
                Text((state as RegistrationUiState.RegistrationError).error)
            is RegistrationUiState.ForgotPasswordSuccess ->
                Text((state as RegistrationUiState.ForgotPasswordSuccess).message)
            is RegistrationUiState.ForgotPasswordError ->
                Text((state as RegistrationUiState.ForgotPasswordError).error)
            RegistrationUiState.Idle -> {} // do nothing
        }
    }
}
