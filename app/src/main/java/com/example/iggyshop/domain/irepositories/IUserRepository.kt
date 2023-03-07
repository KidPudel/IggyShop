package com.example.iggyshop.domain.irepositories

import com.example.iggyshop.data.database.model.UserDBModel

interface IUserRepository {
    /**
     * get user from database based on email
     * @param email unique email of the user by which user is retrieved
     */
    suspend fun getUserFromDatabase(email: String): UserDBModel?

    /**
     * add user to database
     * @param user what user should be added to the database
     */
    suspend fun addUserToDatabase(user: UserDBModel)
}