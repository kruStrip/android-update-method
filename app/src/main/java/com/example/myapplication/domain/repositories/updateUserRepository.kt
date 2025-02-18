package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.models.User

interface updateUserRepository {
    suspend fun updateUser(user: User)
}