package com.example.notes

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.model.NoteViewModel
import com.example.notes.model.Notes
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteAdapter(val context : Context, val onClickInterface : OnClickInterface, val onDeleteInterface : OnDeleteInterface,
                  private val allNotes: ArrayList<Notes>
) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.title)
        val timeStamp : TextView = itemView.findViewById(R.id.description)
        val delButton : ImageView = itemView.findViewById(R.id.delButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_view,parent,false))
        view.delButton.setOnClickListener{
            onDeleteInterface.OnDeleteClick(allNotes[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = allNotes[position]
        holder.title.text = item.title
        holder.timeStamp.text = "Last Updated : " + item.timeStamp
        holder.delButton.setImageResource(R.drawable.ic_baseline_delete_24)
        holder.itemView.setOnClickListener{
            onClickInterface.OnNoteClick(item)
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(noteList : List<Notes>)
    {
        allNotes.clear()
        allNotes.addAll(noteList)
        notifyDataSetChanged()
    }
}
interface OnClickInterface{
    fun OnNoteClick(note : Notes)
}
interface OnDeleteInterface{
    fun OnDeleteClick(note : Notes)
}