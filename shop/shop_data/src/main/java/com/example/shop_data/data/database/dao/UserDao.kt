package com.example.shop_data.data.database.dao

import androidx.room.*
import com.example.shop_data.data.database.model.UserDBModel

@Dao
interface UserDao {
    // debugging
    @Query(value = "SELECT * FROM user")
    suspend fun getAllUsers() : List<UserDBModel>

    // get (check/login)
    @Query(value = "SELECT * FROM user WHERE email LIKE :email")
    suspend fun getUser(email: String) : UserDBModel?

    // insert (register)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUser(user: UserDBModel)
}