package com.example.iggyshop.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iggyshop.data.database.UsersDatabase
import com.example.iggyshop.data.repositories.UserRepository
import com.example.iggyshop.domain.irepositories.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun getUserRepository(userRepository: UserRepository): IUserRepository

    companion object {
        @Provides
        @Singleton
        fun getDatabase(application: Application): UsersDatabase {
            return Room.databaseBuilder(
                context = application,
                UsersDatabase::class.java,
                "users.db"
            ).build()
        }
    }
}