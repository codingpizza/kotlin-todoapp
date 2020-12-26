package com.codingpizza.todoapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codingpizza.todoapp.domain.model.Note

@Database(entities = [Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}