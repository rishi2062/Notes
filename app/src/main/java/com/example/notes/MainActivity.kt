package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.Dao.NoteDao
import com.example.notes.Database.NoteDatabase
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.model.NoteViewModel
import com.example.notes.model.NoteViewModelFactory
import com.example.notes.model.Notes
import com.example.notes.repository.NoteRepository

class MainActivity : AppCompatActivity(), OnClickInterface, OnDeleteInterface {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : NoteViewModel
    private lateinit var db : NoteDatabase
    private lateinit var repo : NoteRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         val notes = ArrayList<Notes>()
        db = NoteDatabase.getDatabase(this)
        repo = NoteRepository(db.notesDao())

        viewModel = ViewModelProvider(this,NoteViewModelFactory(repo))[NoteViewModel::class.java]

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this,this,this,notes)
        binding.recyclerView.adapter = adapter
        viewModel.getNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)
            }
        })
        binding.add.setOnClickListener{
            val intent = Intent(this@MainActivity,NoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun OnNoteClick(note: Notes) {
        val intent = Intent(this@MainActivity,NoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.title)
        intent.putExtra("noteDescription",note.description)
        intent.putExtra("noteId",note.id)
        startActivity(intent)
        this.finish()
    }

    override fun OnDeleteClick(note: Notes) {
        viewModel.deleteNote(note)
    }
}