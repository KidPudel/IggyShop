package com.example.shop_data.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.shop_domain.domain.models.User

@Entity(tableName = "user", indices = [Index(value = ["email"], unique = true)])
data class UserDBModel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
)

// map UserDBModel to User
fun UserDBModel.toUser(): User {
    return User(
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}

fun UserDBModel.toUserDBModel(user: User): UserDBModel {
    return UserDBModel(
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email
    )
}

