package com.codingpizza.todoapp.presentation.createtodo

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.codingpizza.todoapp.presentation.TodoForm
import com.codingpizza.todoapp.presentation.TodoLoading
import com.codingpizza.todoapp.presentation.TodoSavedSuccessFully

const val CREATE_TODO_ROUTE = "createtodo"

@Composable
fun CreateTodoScreen(
    viewModel: CreateTodoViewModel = viewModel(),
    navController: NavHostController
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold {
        when (state) {
            CreateTodoState.FormError -> TODO()
            CreateTodoState.Loading -> TodoLoading()
            CreateTodoState.TodoSavedSuccess -> TodoSavedSuccessFully(navController = navController)
            is CreateTodoState.CurrentTodo -> {
                CreateTodoContent(state as CreateTodoState.CurrentTodo, viewModel)
            }
        }
    }
}

@Composable
fun CreateTodoContent(
    state: CreateTodoState.CurrentTodo,
    viewModel: CreateTodoViewModel
) {
    TodoForm(
        titleValue = state.title,
        onTitleValueChange = { textFieldValue -> viewModel.setTitleValue(textFieldValue) },
        contentValue = state.content,
        onContentValueChange = { viewModel.setContentValue(it) },
        onButtonClick = {
            viewModel.storeNote(
                title = state.title,
                content = state.content
            )
        }
    )
}