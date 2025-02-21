package com.example.myapplication.presentaition.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.usecases.AddUserUseCase
import kotlinx.coroutines.launch

class AddUserViewModel(private val addUserUseCase: AddUserUseCase) : ViewModel() {

    fun addUser(user: User) = viewModelScope.launch {
        addUserUseCase(user = user)
    }
}