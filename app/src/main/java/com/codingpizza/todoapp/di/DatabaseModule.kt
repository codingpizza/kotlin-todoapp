package com.codingpizza.todoapp.di

import android.content.Context
import androidx.room.Room
import com.codingpizza.todoapp.data.datasource.local.AppDatabase
import com.codingpizza.todoapp.data.datasource.local.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "TODOAPP"


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseDao(database: AppDatabase) : NoteDao {
        return database.noteDao()
    }
}