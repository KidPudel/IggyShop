package com.example.iggyshop.domain.use_cases

import com.example.iggyshop.data.database.model.toUser
import com.example.iggyshop.data.repositories.UserRepository
import com.example.iggyshop.domain.irepositories.IUserRepository
import com.example.iggyshop.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepository: IUserRepository) {
    suspend fun getUserFromDatabase(email: String): Flow<User?> = flow {
        val userFromDatabase = userRepository.getUserFromDatabase(email)
        // if everything is okay, returns User, otherwise null
        emit(value = userFromDatabase?.toUser())
    }
}