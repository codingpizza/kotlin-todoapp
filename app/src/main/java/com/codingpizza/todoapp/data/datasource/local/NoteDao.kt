package com.codingpizza.todoapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codingpizza.todoapp.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note WHERE uid = :id")
    suspend fun getNoteById(id: Int): Note

    @Query("UPDATE Note SET title = :title, content = :content WHERE  uid = :id")
    suspend fun updateNote(title: String, content: String, id: Int)

}