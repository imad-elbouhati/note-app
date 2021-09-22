package com.imadev.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.imadev.noteapp.data.local.entities.Note


@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {


    abstract fun noteDao(): NoteDao

}