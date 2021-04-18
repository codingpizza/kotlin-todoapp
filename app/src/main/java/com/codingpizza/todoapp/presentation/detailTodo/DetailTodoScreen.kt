package com.codingpizza.todoapp.presentation.detailTodo

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.codingpizza.todoapp.presentation.TodoForm
import com.codingpizza.todoapp.presentation.TodoLoading
import com.codingpizza.todoapp.presentation.TodoSavedSuccessFully


const val DETAIL_SCREEN_ROUTE = "detail/{id}"
const val DETAIL_SCREEN_ROUTE_ID_PARAM_KEY = "id"

@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel) {
    val id = navController.currentBackStackEntry?.arguments?.getInt(DETAIL_SCREEN_ROUTE_ID_PARAM_KEY)
    val state by viewModel.uiState.collectAsState()
    viewModel.retrieveNote(id)
    Scaffold {
        when(state) {
            DetailUiState.Error -> TODO()
            DetailUiState.Loading -> TodoLoading()
            is DetailUiState.Success -> TodoSavedSuccessFully(navController = navController)
            is DetailUiState.CurrentTodo -> {
                UpdateTodoContent(viewModel, state as DetailUiState.CurrentTodo)
            }
        }
    }
}

@Composable
fun UpdateTodoContent(viewModel: DetailViewModel, state: DetailUiState.CurrentTodo) {
    TodoForm(
        titleValue = state.note.title,
        onTitleValueChange = { textFieldValue -> viewModel.setTitleValue(textFieldValue) },
        contentValue = state.note.content,
        onContentValueChange = { viewModel.setContentValue(it) },
        onButtonClick = {
            viewModel.updateNote(
                title = state.note.title,
                content = state.note.content,
                id = state.note.uid
            )
        }
    )
}
