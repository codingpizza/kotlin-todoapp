package com.codingpizza.todoapp.presentation.todolist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codingpizza.todoapp.databinding.ListFragmentBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class ListFragment : Fragment() {

    private var binding: ListFragmentBinding? = null
    private val viewModel: ListViewModel by inject()
    private val todoListAdapter = TodoListAdapter {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(it.uid))
    }

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
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state -> handleState(state) }
        }
        viewModel.retrieveTodos()
    }

    private fun setupUI() {
        binding?.apply {
            with(noteList) {
                layoutManager = LinearLayoutManager(context)
                adapter = todoListAdapter
            }
            createButton.setOnClickListener {
                findNavController().navigate(ListFragmentDirections.actionListFragmentToCreateTodoFragment())
            }
        }
    }

    private fun handleState(state: ListUiState) {
        when (state) {
            is ListUiState.Success -> onStateSuccess(state)
            is ListUiState.Error -> TODO()
            ListUiState.Loading -> Log.d("ListFragment", "Cargando informacion")
        }
    }

    private fun onStateSuccess(state: ListUiState.Success) {
        todoListAdapter.submitList(state.listNote)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.noteList?.adapter = null
        binding = null
    }
}