package br.com.myapplication.presentation

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.myapplication.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _uiState =  MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> =  _uiState.asStateFlow()

    fun onLoginChange(login: String) {
        _uiState.update { it.copy(login = login) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun submit() {
        viewModelScope.launch {
            if (uiState.value.isFormValid) {
                val result = authRepository.login(
                    uiState.value.login,
                    uiState.value.password
                )
                result.fold(
                    onSuccess = { success ->
                        if (success) {
                            _uiState.update { it.copy(isUserValid = true) }
                        }
                    },
                    onFailure = {}
                )
            }
        }
    }
}

data class LoginUiState(
    val login: String = "",
    val password: String = "",
    val isUserValid: Boolean = false
) {
    val isFormValid: Boolean = login.isNotBlank() && password.isNotBlank()


}


