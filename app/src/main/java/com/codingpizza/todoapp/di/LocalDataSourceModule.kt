package com.codingpizza.todoapp.di

import com.codingpizza.todoapp.data.datasource.NoteDataSource
import com.codingpizza.todoapp.data.datasource.local.LocalNoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LocalDataSource

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataSourceModule {

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun bindLocalDataSource(implementation: LocalNoteDataSource) : NoteDataSource

}