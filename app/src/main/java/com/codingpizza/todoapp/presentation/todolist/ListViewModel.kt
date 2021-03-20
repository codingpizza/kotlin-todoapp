package com.codingpizza.todoapp.presentation.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingpizza.todoapp.domain.repository.NoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<ListUiState> = MutableStateFlow(ListUiState.Loading)
    val uiState: StateFlow<ListUiState> = _uiState

    fun retrieveTodos() {
        viewModelScope.launch {
            noteRepository.retrieveNotes().collect { list ->
                _uiState.value = ListUiState.Success(list)
            }
        }
    }

}