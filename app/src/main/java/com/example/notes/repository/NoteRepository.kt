package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.Dao.NoteDao
import com.example.notes.model.Notes

class NoteRepository(val noteDao : NoteDao) {

    fun getAllNotes() : LiveData<List<Notes>> = noteDao.getAllNotes()
    suspend fun insertNote(note : Notes) = noteDao.insertNote(note)
    suspend fun deleteNote(note : Notes) = noteDao.deleteNote(note)
    suspend fun updateNote(note : Notes) = noteDao.updateNote(note)
}