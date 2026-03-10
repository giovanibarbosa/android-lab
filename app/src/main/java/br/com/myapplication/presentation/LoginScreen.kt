package br.com.myapplication.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {
    val uiState = viewModel.uiState.collectAsState()

//    val onLogin by rememberUpdatedState()

    if (uiState.value.isUserValid) {
        HomeScreen()
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            OutlinedTextField(
                value = uiState.value.login,
                label = { Text("Login") },
                onValueChange = viewModel::onLoginChange
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.value.password,
                label = { Text("Password") },
                onValueChange = viewModel::onPasswordChange,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = viewModel::submit
            ) {
                Text(
                    text = "Enter"
                )
            }
        }
    }


}
