package com.codingpizza.todoapp.presentation.createtodo

sealed class CreateTodoState {
    object TodoSavedSuccess : CreateTodoState()
    object FormError : CreateTodoState()
    object Loading : CreateTodoState()
    data class CurrentTodo(val title: String, val content: String) : CreateTodoState()
}
