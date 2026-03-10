package br.com.myapplication.domain

import androidx.compose.ui.graphics.RectangleShape
import br.com.myapplication.data.AuthRepository

class AuthRepositoryImpl: AuthRepository {
    override fun login(login: String, email: String): Result<Boolean> {
        return Result.success(true)
    }
}