package com.codingpizza.todoapp.presentation.createtodo

sealed class CreateTodoState {
    object Success : CreateTodoState()
    object FormError : CreateTodoState()
    object Loading : CreateTodoState()
}
