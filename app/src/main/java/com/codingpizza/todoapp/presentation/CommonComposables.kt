package com.codingpizza.todoapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codingpizza.todoapp.presentation.todolist.CenterBox
import kotlinx.coroutines.delay

@Composable
fun TodoLoading() {
    CenterBox {
        CircularProgressIndicator()
    }
}

@Composable
fun TodoForm(
    titleValue: String,
    onTitleValueChange: (String) -> Unit,
    contentValue: String,
    onContentValueChange: (String) -> Unit,
    onButtonClick: () -> Unit,
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = titleValue,
                onValueChange = onTitleValueChange,
                label = { Text("Title") },
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = contentValue,
                onValueChange = onContentValueChange,
                label = { Text("Note") },
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun TodoSavedSuccessFully(navController: NavController) {
    CenterBox {
        Text(text = "Note guardada exitosamente")
        LaunchedEffect(key1 = "key1", block = {
            delay(3000)
            navController.popBackStack()
        })
    }
}