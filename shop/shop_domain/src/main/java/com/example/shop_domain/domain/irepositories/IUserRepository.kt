package com.example.shop_domain.domain.irepositories

import com.example.shop_domain.domain.models.User

interface IUserRepository {
    /**
     * get user from database based on email
     * @param email unique email of the user by which user is retrieved
     */
    suspend fun getUserFromDatabase(email: String): User?

    /**
     * add user to database
     * @param user what user should be added to the database
     */
    suspend fun addUserToDatabase(user: User)

}