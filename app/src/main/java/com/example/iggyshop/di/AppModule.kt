package com.example.iggyshop.di

import android.app.Application
import androidx.room.Room
import com.example.iggyshop.common.Constants.BASE_URL
import com.example.shop_data.data.remote.IGoodsApi
import com.example.shop_data.data.repositories.GoodsRepository
import com.example.shop_data.data.repositories.UserRepository
import com.example.shop_data.data.database.UsersDatabase
import com.example.shop_domain.domain.irepositories.IGoodsRepository
import com.example.shop_domain.domain.irepositories.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun getUserRepository(userRepository: UserRepository): IUserRepository

    @Binds
    @Singleton
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