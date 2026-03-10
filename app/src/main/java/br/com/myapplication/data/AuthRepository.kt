package br.com.myapplication.data

interface AuthRepository {
    fun login(login: String, email: String): Result<Boolean>
}
