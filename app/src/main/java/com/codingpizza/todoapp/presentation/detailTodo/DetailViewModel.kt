package com.codingpizza.todoapp.presentation.detailTodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingpizza.todoapp.domain.model.Note
import com.codingpizza.todoapp.domain.repository.NoteRepository
import com.codingpizza.todoapp.presentation.createtodo.CreateTodoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState.Loading)

    val uiState: StateFlow<DetailUiState> = _uiState

    fun retrieveNote(id: Int) {
        viewModelScope.launch {
            val note: Note = repository.retrieveNote(id)
            _uiState.value = DetailUiState.Success(note)
        }
    }

    fun updateNote(title: String?, content: String?, id: Int) {
        viewModelScope.launch {
            val modifiedNote = Note(id, title ?: "", content ?: "")
            repository.updateNote(modifiedNote)
            _uiState.value = DetailUiState.UpdateNote(modifiedNote)
        }

    }

}