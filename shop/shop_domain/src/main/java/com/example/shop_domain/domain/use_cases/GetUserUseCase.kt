package com.example.shop_domain.domain.use_cases

import com.example.shop_domain.domain.irepositories.IUserRepository
import com.example.shop_domain.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: IUserRepository) {
    suspend fun getUserFromDatabase(email: String): Flow<User?> = flow {
        val userFromDatabase = userRepository.getUserFromDatabase(email)
        // if everything is okay, returns User, otherwise null
        emit(value = userFromDatabase)
    }

}