package com.example.iggyshop.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.iggyshop.common.Constants.BASE_URL
import com.example.iggyshop.data.database.UsersDatabase
import com.example.iggyshop.data.remote.IGoodsApi
import com.example.iggyshop.data.repositories.GoodsRepository
import com.example.iggyshop.data.repositories.UserRepository
import com.example.iggyshop.domain.irepositories.IGoodsRepository
import com.example.iggyshop.domain.irepositories.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun getUserRepository(userRepository: UserRepository): IUserRepository

    fun getGoodsRepository(repository: GoodsRepository): IGoodsRepository

    companion object {
        // get database instance
        @Provides
        @Singleton
        fun getDatabase(application: Application): UsersDatabase {
            return Room.databaseBuilder(
                context = application,
                UsersDatabase::class.java,
                name = "users.db"
            ).build()
        }

        // get retrofit instance

        @Provides
        @Singleton
        fun getRetrofit(): IGoodsApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IGoodsApi::class.java)
        }
    }
}