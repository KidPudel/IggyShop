package com.example.shop_data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shop_data.data.database.dao.UserDao
import com.example.shop_data.data.database.model.UserDBModel

@Database(entities = [UserDBModel::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

}