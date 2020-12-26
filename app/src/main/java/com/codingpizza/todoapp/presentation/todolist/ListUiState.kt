package com.codingpizza.todoapp.presentation.todolist

import com.codingpizza.todoapp.domain.model.Note

sealed class ListUiState {
    data class Success(val listNote: List<Note>) : ListUiState()

    data class Error(val errorMessage: String) : ListUiState()

    object Loading : ListUiState()
}
