package com.example.roomexample.di

import android.content.Context
import androidx.room.Room
import com.example.roomexample.data.datasource.local.services.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDBInstance(@ApplicationContext context: Context): ContactDatabase =
        Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contactDB"
        ).build()

    @Provides
    @Singleton
    fun provideDAO(db : ContactDatabase) = db.dao

}