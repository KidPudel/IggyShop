package com.example.shop_domain.domain.use_cases

import com.example.shop_domain.domain.irepositories.IUserRepository
import com.example.shop_domain.domain.models.User
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepository: IUserRepository) {
    suspend fun addUserToDatabase(user: User) {
        userRepository.addUserToDatabase(user)
    }
}