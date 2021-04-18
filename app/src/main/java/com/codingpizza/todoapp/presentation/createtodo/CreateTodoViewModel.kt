package com.codingpizza.todoapp.presentation.createtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingpizza.todoapp.domain.model.Note
import com.codingpizza.todoapp.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateTodoViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<CreateTodoState> =
        MutableStateFlow(CreateTodoState.CurrentTodo(title = "",content = ""))

    val uiState: StateFlow<CreateTodoState> = _uiState

    fun storeNote(title: String?, content: String?) {
        if (title.isNullOrEmpty() || content.isNullOrEmpty()) {
            _uiState.value = CreateTodoState.FormError
        } else {
            viewModelScope.launch {
                repository.createNote(Note(title = title, content = content))
                _uiState.value = CreateTodoState.TodoSavedSuccess
            }
        }
    }

    fun setTitleValue(title: String) {
        val textState = (_uiState.value as CreateTodoState.CurrentTodo)
        _uiState.value = CreateTodoState.CurrentTodo(title = title, content = textState.content)
    }

    fun setContentValue(content: String) {
        val textState = (_uiState.value as CreateTodoState.CurrentTodo)
        _uiState.value = CreateTodoState.CurrentTodo(title = textState.title, content = content)
    }
}