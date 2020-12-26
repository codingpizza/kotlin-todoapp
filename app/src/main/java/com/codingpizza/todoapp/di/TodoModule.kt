package com.codingpizza.todoapp.di

import androidx.room.Room
import com.codingpizza.todoapp.data.datasource.NoteDataSource
import com.codingpizza.todoapp.data.datasource.local.AppDatabase
import com.codingpizza.todoapp.data.datasource.local.LocalNoteDataSource
import com.codingpizza.todoapp.domain.repository.NoteRepository
import com.codingpizza.todoapp.presentation.todolist.ListViewModel
import com.codingpizza.todoapp.presentation.createtodo.CreateTodoViewModel
import com.codingpizza.todoapp.presentation.detailTodo.DetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val NOTE_DATASOURCE_LOCAL: String = "LocalNoteDataSource"
private const val NOTE_REPOSITORY: String = "NoteRepository"
private const val ROOM_DATABASE: String = "RoomDatabase"
private const val DATABASE_NAME = "TODOAPP"

val todoModule = module {

    single(named(ROOM_DATABASE)) {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<NoteDataSource>(named(NOTE_DATASOURCE_LOCAL)) {
        LocalNoteDataSource(
            get(
                named(
                    ROOM_DATABASE
                )
            )
        )
    }

    single(named(NOTE_REPOSITORY)) {
        NoteRepository(
            localDataSource = get(
                named(
                    NOTE_DATASOURCE_LOCAL
                )
            )
        )
    }

    viewModel { ListViewModel(noteRepository = get(named(NOTE_REPOSITORY))) }

    viewModel { CreateTodoViewModel(repository = get(named(NOTE_REPOSITORY))) }

    viewModel { DetailViewModel(repository = get(named(NOTE_REPOSITORY))) }
}