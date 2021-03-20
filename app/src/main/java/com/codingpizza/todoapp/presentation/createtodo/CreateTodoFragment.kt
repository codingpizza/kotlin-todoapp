package com.codingpizza.todoapp.presentation.createtodo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.codingpizza.todoapp.databinding.CreateTodoFragmentBinding
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class CreateTodoFragment : Fragment() {

    private var binding: CreateTodoFragmentBinding? = null
    private val viewModel: CreateTodoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateTodoFragmentBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storeNote()
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state -> handleState(state) }
        }
    }

    private fun handleState(state: CreateTodoState) {
        when (state) {
            CreateTodoState.TodoSavedSuccess -> onSuccess()
            CreateTodoState.FormError -> onFormError()
            CreateTodoState.Loading -> {
                Log.d("CreateTodoFragment", "Todo")
            }
        }
    }

    private fun onFormError() {
        Toast.makeText(context, "Invalid fields", Toast.LENGTH_SHORT).show()
    }

    private fun onSuccess() {
        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack()
    }

    private fun storeNote() {
        binding?.saveButton?.setOnClickListener {
            viewModel.storeNote(
                title = binding?.todoTitle?.text.toString(),
                content = binding?.todoContent?.text.toString()
            )
        }
    }


}