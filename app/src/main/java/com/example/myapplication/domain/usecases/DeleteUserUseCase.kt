package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.repositories.deleteUserRepository

class DeleteUserUseCase(private val DeleteUserRepository: deleteUserRepository) {
    suspend fun execute(user: User) {
        DeleteUserRepository.deleteUser(user)
    }
}