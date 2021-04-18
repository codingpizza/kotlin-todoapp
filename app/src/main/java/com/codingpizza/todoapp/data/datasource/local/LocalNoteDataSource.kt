package com.codingpizza.todoapp.data.datasource.local

import com.codingpizza.todoapp.data.datasource.NoteDataSource
import com.codingpizza.todoapp.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalNoteDataSource @Inject constructor(private val database: AppDatabase) : NoteDataSource {

    override fun retrieveNotes(): Flow<List<Note>> = database.noteDao().getAllNotes()

    override suspend fun storeNote(note: Note) = database.noteDao().insertNote(note)

    override suspend fun retrieveNote(id: Int): Note = database.noteDao().getNoteById(id)

    override suspend fun updateNote(modifiedNote: Note) {
        database.noteDao().updateNote(
            title = modifiedNote.title,
            content = modifiedNote.content,
            id = modifiedNote.uid
        )
    }

}