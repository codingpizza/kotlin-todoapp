package com.codingpizza.todoapp.presentation.createtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingpizza.todoapp.domain.model.Note
import com.codingpizza.todoapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateTodoViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<CreateTodoState> =
        MutableStateFlow(CreateTodoState.Loading)

    val uiState: StateFlow<CreateTodoState> = _uiState

    fun storeNote(title: String?, content: String?) {
        if (title.isNullOrEmpty() || content.isNullOrEmpty()) {
            _uiState.value = CreateTodoState.FormError
        } else {
            viewModelScope.launch {
                repository.createNote(Note(title = title, content = content))
                _uiState.value = CreateTodoState.Success
            }
        }
    }
}