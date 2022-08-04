package com.example.notes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Notes(
    @PrimaryKey(autoGenerate = true) var id : Int,
    @ColumnInfo(name = "Title")val title : String,
    @ColumnInfo(name="Detail")val description : String,
    val timeStamp: String)