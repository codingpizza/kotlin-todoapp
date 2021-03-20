package com.codingpizza.todoapp.presentation.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.codingpizza.todoapp.databinding.ListFragmentBinding
import com.codingpizza.todoapp.domain.model.Note
import com.codingpizza.todoapp.presentation.TodoLoading
import com.codingpizza.todoapp.presentation.createtodo.CREATE_TODO_ROUTE
import com.codingpizza.todoapp.presentation.createtodo.CreateTodoScreen
import com.codingpizza.todoapp.presentation.createtodo.CreateTodoViewModel
import org.koin.android.ext.android.inject

class ListFragment : Fragment() {

    private var binding: ListFragmentBinding? = null
    private val viewModel: ListViewModel by inject()
    private val todoViewModel : CreateTodoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding?.apply {
            composeView.setContent{
                MaterialTheme {
                    val navController = rememberNavController()
                    ListScreen(
                        navController = navController,
                        viewModel = viewModel,
                        todoViewModel = todoViewModel
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

@Composable
fun ListScreen(
    navController: NavHostController,
    viewModel: ListViewModel,
    todoViewModel: CreateTodoViewModel
) {
    NavHost(navController = navController, startDestination = "list") {

        composable("list") {
            TodoLazyColumn(navController,viewModel = viewModel)
        }

        composable(CREATE_TODO_ROUTE) {
            CreateTodoScreen(navController = navController,viewModel = todoViewModel)
        }
    }
}

@Composable
fun TodoLazyColumn(navController: NavHostController, viewModel: ListViewModel) {
    viewModel.retrieveTodos()
    val listUiState by viewModel.uiState.collectAsState()
    when (listUiState){
        is ListUiState.Success -> TodoList(navController, listUiState as ListUiState.Success)
        is ListUiState.Error -> ErrorText(listUiState = listUiState as ListUiState.Error)
        ListUiState.Loading -> TodoLoading()
    }
}

@Composable
fun ErrorText(listUiState: ListUiState.Error){
    CenterBox {
        Text(text = "Ha ocurrido un error :( ${listUiState.errorMessage}")
    }
}

@Composable
fun CenterBox(content: @Composable () -> Unit ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        content()
    }
}

@Composable
fun TodoList(navController: NavHostController, listUiState: ListUiState.Success) {
    Scaffold(floatingActionButton = {
        TodoFloatingActionButton(onClick = { navController.navigate(CREATE_TODO_ROUTE)})
    }) {
        Column {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(all = 16.dp)) {
                items(items =
                listUiState.listNote
                ){ note ->
                    TodoRow(note = note)
                }
            }
        }
    }
}

@Composable
fun TodoFloatingActionButton(onClick : () -> Unit)  {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Edit, contentDescription = "Create new todo")
    }
}

@Composable
fun TodoRow(note : Note) {
    Card(elevation = 4.dp, modifier = Modifier.padding(top = 16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = note.title,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp),
                color = Color.Black
            )
            Text(
                text = note.content,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, bottom = 8.dp),
                color = Color.Gray
            )
        }
    }
}

