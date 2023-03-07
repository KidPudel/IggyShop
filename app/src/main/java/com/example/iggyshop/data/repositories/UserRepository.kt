package com.example.iggyshop.data.repositories

import com.example.iggyshop.data.database.UsersDatabase
import com.example.iggyshop.data.database.model.UserDBModel
import com.example.iggyshop.domain.irepositories.IUserRepository
import javax.inject.Inject

class UserRepository @Inject constructor(private val usersDatabase: UsersDatabase) : IUserRepository {
    override suspend fun getUserFromDatabase(email: String): UserDBModel? {
        return usersDatabase.userDao().getUser(email = email)
    }

    override suspend fun addUserToDatabase(user: UserDBModel) {
        usersDatabase.userDao().insertUser(user = user)
    }
}