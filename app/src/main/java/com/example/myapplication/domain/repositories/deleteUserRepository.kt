package com.example.myapplication.domain.repositories

import com.example.myapplication.domain.models.User

interface deleteUserRepository {
    suspend fun deleteUser(user: User)
}