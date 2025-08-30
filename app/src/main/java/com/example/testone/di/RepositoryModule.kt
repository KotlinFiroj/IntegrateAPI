package com.example.testone.di

import com.example.testone.data.remote.ApiService
import com.example.testone.data.repository.ListRepositoryImpl
import com.example.testone.data.repository.RegistrationRepositoryImpl
import com.example.testone.domain.repository.ListRepository
import com.example.testone.domain.repository.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideListRepository(apiService: ApiService): ListRepository {
        return ListRepositoryImpl(apiService)
    }

    @Provides
    fun provideRegisterRepo(apiService: ApiService): RegistrationRepository {
        return RegistrationRepositoryImpl(apiService)
    }
}
