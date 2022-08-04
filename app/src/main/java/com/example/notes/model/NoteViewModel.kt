package com.example.notes.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notes.repository.NoteRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NoteViewModel(val repository: NoteRepository) : ViewModel() {
    val getNotes : LiveData<List<Notes>> = repository.getAllNotes()
    fun insertNote(note: Notes) = GlobalScope.launch {
        repository.insertNote(note)
    }
    fun deleteNote(note:Notes) = GlobalScope.launch {
        repository.deleteNote(note)
    }
    fun updataNote(note:Notes) = GlobalScope.launch {
        repository.updateNote(note)
    }

}