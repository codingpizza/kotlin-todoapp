package com.codingpizza.todoapp.presentation.detailTodo

import com.codingpizza.todoapp.domain.model.Note

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val note: Note) : DetailUiState()
    data class UpdateNote(val note: Note) : DetailUiState()
    object Error : DetailUiState()
}
