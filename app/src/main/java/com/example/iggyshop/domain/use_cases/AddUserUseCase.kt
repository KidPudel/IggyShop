package com.example.iggyshop.domain.use_cases

import com.example.iggyshop.data.repositories.UserRepository
import com.example.iggyshop.domain.irepositories.IUserRepository
import com.example.iggyshop.domain.models.User
import com.example.iggyshop.domain.models.toUserDBModel
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: IUserRepository) {
    suspend fun addUserToDatabase(user: User) {
        userRepository.addUserToDatabase(user.toUserDBModel())
    }
}