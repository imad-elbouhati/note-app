package com.imadev.noteapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import java.util.*


@Entity(tableName = "notes")
data class Note(
    val title: String,
    val content: String,
    val color: String,
    val owners: List<String>,
    val date: Long,
    @Expose(deserialize = false, serialize = false)
    val isSynced: Boolean = false,
    @PrimaryKey(autoGenerate = false)
    val id: String = UUID.randomUUID().toString()
)