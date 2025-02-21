package com.example.myapplication.data.repositories

import com.example.myapplication.data.models.UserDto
import com.example.myapplication.domain.models.User
import com.example.myapplication.domain.repositories.getUsersRepository
import com.example.myapplication.domain.repositories.addUserRepository
import com.example.myapplication.domain.repositories.deleteUserRepository
import com.example.myapplication.domain.repositories.updateUserRepository

class getUsersRepositoryImpl: getUsersRepository, addUserRepository, updateUserRepository, deleteUserRepository {
    val listUsers = mutableListOf(
        UserDto(1, "Harry", "Potter", 13),
        UserDto(2, "Sirius", "Black", 33),
    )

    override suspend fun getUsers(): List<User> {

        return listUsers.map{ dto ->
            User(
                name = dto.name,
                surname = dto.surname,
                age = dto.age
            )

        }
    }

    override suspend fun addUser(user: User) {
        listUsers.add(UserDto(id=3, name = user.name, surname = user.surname, age = user.age))
    }

    override suspend fun updateUser(user: User) {
        val existingUser = listUsers.find { it.name == user.name }

        if (existingUser != null) {
            listUsers[listUsers.indexOf(existingUser)] = existingUser.copy(name = user.name, surname = user.surname, age = user.age)
        }
    }
    override suspend fun deleteUser(user:User) {
        listUsers.removeIf { it.name == user.name }
    }

}