package com.example.iggyshop.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.iggyshop.data.database.model.UserDBModel

@Database(entities = [UserDBModel::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
}