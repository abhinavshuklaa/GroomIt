package com.avenger.timesaver.di

import com.avenger.timesaver.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesRepository(): AppRepository {
        return AppRepository()
    }

}