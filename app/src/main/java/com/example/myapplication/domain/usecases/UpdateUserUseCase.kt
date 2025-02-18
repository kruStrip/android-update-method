package com.example.myapplication.domain.usecases

import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.repositories.updateUserRepository

class UpdateUserUseCase(private val updateUserRepository: updateUserRepository){
    suspend operator fun invoke(user: User) {
        return updateUserRepository.updateUser(user)
    }
}