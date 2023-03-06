package com.example.iggyshop.data.database.dao

import androidx.room.*
import com.example.iggyshop.data.database.model.UserDBModel

@Dao
interface UserDao {
    // debugging
    @Query(value = "SELECT * FROM user")
    fun getAllUsers() : List<UserDBModel>

    // get (check/login)
    @Query(value = "SELECT * FROM user WHERE email LIKE :email")
    fun getUser(email: String) : UserDBModel

    // insert (register)
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: UserDBModel)
}