package com.example.myapplication.presentaition.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.usecases.UpdateUserUseCase
import kotlinx.coroutines.launch

class UpdateUserViewModel(private val updateUserUseCase: UpdateUserUseCase) : ViewModel() {
    fun updateUser(user: User) = viewModelScope.launch {
        updateUserUseCase(user = user)
    }
}