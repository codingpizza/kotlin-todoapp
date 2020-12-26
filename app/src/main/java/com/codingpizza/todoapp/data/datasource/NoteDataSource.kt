package com.codingpizza.todoapp.data.datasource

import com.codingpizza.todoapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {
    fun retrieveNotes(): Flow<List<Note>>
    suspend fun storeNote(note: Note)
    suspend fun retrieveNote(id: Int): Note
    suspend fun updateNote(modifiedNote: Note)
}