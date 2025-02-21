package com.example.myapplication.presentaition.viewmodels

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repositories.getUsersRepositoryImpl
import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.usecases.DeleteUserUseCase

class DeleteUserViewModel(private val deleteUserUseCase: DeleteUserUseCase) : ViewModel() {
    suspend fun deleteUser(user: User) {
        deleteUserUseCase.execute(user)
    }
}

