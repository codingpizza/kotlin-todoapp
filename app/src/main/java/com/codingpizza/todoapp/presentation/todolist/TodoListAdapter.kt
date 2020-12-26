package com.codingpizza.todoapp.presentation.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingpizza.todoapp.databinding.TodoItemListBinding
import com.codingpizza.todoapp.domain.model.Note

class TodoListAdapter(private val onClick: (Note) -> Unit) :
    RecyclerView.Adapter<TodoListViewHolder>() {
    private val noteList = mutableListOf<Note>()
    private lateinit var binding: TodoItemListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        binding = TodoItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoListViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) =
        holder.bind(noteList[position])

    override fun getItemCount(): Int = noteList.size

    fun submitList(listNote: List<Note>) {
        noteList.clear()
        noteList.addAll(listNote)
        notifyDataSetChanged()
    }
}