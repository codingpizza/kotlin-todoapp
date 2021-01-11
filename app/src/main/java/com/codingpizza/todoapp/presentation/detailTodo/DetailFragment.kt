package com.codingpizza.todoapp.presentation.detailTodo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.codingpizza.todoapp.R
import com.codingpizza.todoapp.databinding.DetailFragmentBinding
import com.codingpizza.todoapp.domain.model.Note
import com.codingpizza.todoapp.utils.exhaustive
import com.codingpizza.todoapp.utils.showLog
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by inject()
    private lateinit var binding: DetailFragmentBinding
    private val fragmentArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonListener()
        viewModel.retrieveNote(fragmentArgs.id)
        setupObserver()
    }

    private fun setButtonListener() {
        binding.apply {
            button.setOnClickListener {
                if (root.currentState == R.id.start) {
                    root.transitionToEnd()
                }
                else {
                    viewModel.updateNote(
                        title = editTitle.text.toString(),
                        content = editContent.text.toString(),
                        id = fragmentArgs.id
                    )
                    root.transitionToStart()
                }
            }
        }
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { detailUiState ->
                handleState(detailUiState)
            }
        }
    }

    private fun handleState(detailUiState: DetailUiState) {
        when (detailUiState) {
            DetailUiState.Loading -> showLog("Loading Note...")
            is DetailUiState.Success -> onNoteRetrieved(detailUiState.note)
            DetailUiState.Error -> showLog("Error loading...")
            is DetailUiState.UpdateNote -> onNoteRetrieved(detailUiState.note)
        }.exhaustive
    }

    private fun onNoteRetrieved(note: Note) {
        binding.apply {
            title.text = note.title
            todoContent.text = note.content
            editTitle.setText(note.title)
            editContent.setText(note.content)
        }
    }

}