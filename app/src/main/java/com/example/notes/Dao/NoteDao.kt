package com.example.notes.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notes.model.Notes


@Dao
interface NoteDao  {

    @Insert
    suspend fun insertNote(note : Notes)

    @Update
    suspend fun  updateNote(note : Notes)

    @Delete
    suspend fun deleteNote(note : Notes)

    @Query("SELECT * FROM NOTES ORDER BY id")
    fun getAllNotes() : LiveData<List<Notes>>
}