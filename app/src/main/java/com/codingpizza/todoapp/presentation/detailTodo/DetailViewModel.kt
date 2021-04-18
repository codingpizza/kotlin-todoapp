package com.codingpizza.todoapp.presentation.detailTodo

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
class DetailViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<DetailUiState> =
        MutableStateFlow(DetailUiState.Loading)

    val uiState: StateFlow<DetailUiState> = _uiState

    fun retrieveNote(id: Int?) {
        if (id == null) {
            _uiState.value = DetailUiState.Error
        } else {
            viewModelScope.launch {
                val note: Note = repository.retrieveNote(id)
                _uiState.value = DetailUiState.CurrentTodo(note)
            }
        }
    }

    fun updateNote(title: String?, content: String?, id: Int) {
        viewModelScope.launch {
            val modifiedNote = Note(id, title ?: "", content ?: "")
            repository.updateNote(modifiedNote)
            _uiState.value = DetailUiState.Success(modifiedNote)
        }
    }

    fun setTitleValue(title: String) {
        val textState = (uiState.value as DetailUiState.CurrentTodo)
        _uiState.value = DetailUiState.CurrentTodo(Note(uid = textState.note.uid,title = title, content = textState.note.content ))
    }

    fun setContentValue(content: String) {
        val textState = (uiState.value as DetailUiState.CurrentTodo)
        _uiState.value = DetailUiState.CurrentTodo(Note(uid = textState.note.uid,title = textState.note.title, content = content ))
    }

}