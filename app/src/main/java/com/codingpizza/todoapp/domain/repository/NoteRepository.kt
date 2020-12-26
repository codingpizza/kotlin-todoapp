package com.codingpizza.todoapp.domain.repository

import com.codingpizza.todoapp.data.datasource.NoteDataSource
import com.codingpizza.todoapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val localDataSource: NoteDataSource) {

    fun retrieveNotes(): Flow<List<Note>> = localDataSource.retrieveNotes()

    suspend fun createNote(note: Note) = localDataSource.storeNote(note)

    suspend fun retrieveNote(id: Int): Note = localDataSource.retrieveNote(id)

    suspend fun updateNote(modifiedNote: Note) {
        localDataSource.updateNote(modifiedNote)
    }

}