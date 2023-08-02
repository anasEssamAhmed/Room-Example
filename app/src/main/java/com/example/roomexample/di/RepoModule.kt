package com.example.roomexample.di

import com.example.roomexample.data.datasource.local.repository.Repository
import com.example.roomexample.data.datasource.local.services.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideRepo(db : ContactDatabase) = Repository(db.dao)
}