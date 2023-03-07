package com.example.iggyshop.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iggyshop.data.database.dao.UserDao
import com.example.iggyshop.data.database.model.UserDBModel

@Database(entities = [UserDBModel::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

}