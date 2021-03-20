package com.codingpizza.todoapp.presentation

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.codingpizza.todoapp.presentation.todolist.CenterBox

@Composable
fun TodoLoading() {
    CenterBox {
        CircularProgressIndicator()
    }
}