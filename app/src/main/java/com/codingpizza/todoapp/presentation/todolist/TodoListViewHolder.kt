package com.codingpizza.todoapp.presentation.todolist

import androidx.recyclerview.widget.RecyclerView
import com.codingpizza.todoapp.databinding.TodoItemListBinding
import com.codingpizza.todoapp.domain.model.Note

class TodoListViewHolder(
    private val binding: TodoItemListBinding,
    private val onClick: (Note) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.noteCard.setOnClickListener { onClick(note) }
        binding.title.text = note.title
        binding.content.text = note.content
    }

}