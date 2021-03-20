package com.codingpizza.todoapp.presentation.createtodo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.codingpizza.todoapp.presentation.TodoLoading

const val CREATE_TODO_ROUTE = "createtodo"

@Composable
fun CreateTodoScreen(viewModel: CreateTodoViewModel, navController: NavHostController) {
    val state by viewModel.uiState.collectAsState()

    Scaffold {
        when (state) {
            CreateTodoState.FormError -> TODO()
            CreateTodoState.Loading -> TodoLoading()
            CreateTodoState.TodoSavedSuccess -> TODO()
            is CreateTodoState.CurrentTodo -> TodoScreenContent(
                createTodoViewModel = viewModel,
                state = state as CreateTodoState.CurrentTodo
            )
        }
    }
}

@Composable
fun TodoScreenContent(
    state: CreateTodoState.CurrentTodo,
    createTodoViewModel: CreateTodoViewModel
) {
    Column {
        val (textTitle, setTextTitle) = remember { mutableStateOf(TextFieldValue("")) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = TextFieldValue(state.title),
                onValueChange = { textFieldValue -> createTodoViewModel.setTextFieldValue(textFieldValue.text) },
                label = { Text("Title:") },
            )
        }
        val (textNote, setTextNote) = remember { mutableStateOf(TextFieldValue("")) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = textNote,
                onValueChange = { setTextNote(it) },
                label = { Text("Note:") },
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Save")
            }
        }
    }

}