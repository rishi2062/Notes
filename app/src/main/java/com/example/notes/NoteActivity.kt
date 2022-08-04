package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.notes.Database.NoteDatabase
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.databinding.ActivityNoteBinding
import com.example.notes.model.NoteViewModel
import com.example.notes.model.NoteViewModelFactory
import com.example.notes.model.Notes
import com.example.notes.repository.NoteRepository
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    private lateinit var viewModel: NoteViewModel
    private lateinit var db: NoteDatabase
    private lateinit var repo: NoteRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDatabase.getDatabase(this)
        repo = NoteRepository(db.notesDao())
        viewModel = ViewModelProvider(this, NoteViewModelFactory(repo))[NoteViewModel::class.java]

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            //noteId = intent.getIntExtra("noteId", -1)
            binding.title.setText(noteTitle)
            binding.description.setText(noteDescription)
        } else {
            binding.postNote.setText("SAVE")
        }
        binding.postNote.setOnClickListener {
            val noteTitle = binding.title.text.toString()
            val noteDescription = binding.description.text.toString()
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm")
                    val currDate: String = sdf.format(Date())
                    val updateNote = Notes(0,noteTitle, noteDescription, currDate)
                    viewModel.updataNote(updateNote)
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm")
                    val currDate: String = sdf.format(Date())
                    val addNote = Notes(0, noteTitle, noteDescription, currDate)
                    viewModel.insertNote(addNote)
                }
            }
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}