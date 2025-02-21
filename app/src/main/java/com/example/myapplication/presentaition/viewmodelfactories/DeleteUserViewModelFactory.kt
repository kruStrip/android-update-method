package com.example.myapplication.presentaition.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.usecases.DeleteUserUseCase
import com.example.myapplication.presentaition.viewmodels.AddUserViewModel
import com.example.myapplication.presentaition.viewmodels.DeleteUserViewModel

@Suppress("UNCHECKED_CAST")
class DeleteUserViewModelFactory(private val deleteUserUseCase: DeleteUserUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DeleteUserViewModel(deleteUserUseCase) as T
    }
}