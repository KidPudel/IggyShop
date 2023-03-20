package com.example.shop_data.data.repositories

import com.example.shop_data.data.database.UsersDatabase
import com.example.shop_data.data.database.mapper.toUserDBModel
import com.example.shop_data.data.database.model.UserDBModel
import com.example.shop_data.data.database.model.toUser
import com.example.shop_domain.domain.irepositories.IUserRepository
import com.example.shop_domain.domain.models.User
import javax.inject.Inject

class UserRepository @Inject constructor(private val usersDatabase: UsersDatabase) :
    IUserRepository {
    override suspend fun getUserFromDatabase(email: String): User? {
        val userFromDB = usersDatabase.userDao().getUser(email)
        return userFromDB?.toUser()
    }

    override suspend fun addUserToDatabase(user: User) {
        val userDBModel = user.toUserDBModel()
        usersDatabase.userDao().insertUser(userDBModel)
    }

}