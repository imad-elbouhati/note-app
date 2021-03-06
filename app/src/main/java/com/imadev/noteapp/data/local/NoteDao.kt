package com.imadev.noteapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.imadev.noteapp.data.local.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("DELETE FROM NOTES WHERE id = :noteId")
    suspend fun deleteNoteById(noteId: String)

    @Query("DELETE FROM NOTES WHERE isSynced = 1")
    suspend fun deleteAllSyncedNotes()

    @Query("SELECT * FROM NOTES WHERE id = :noteId")
    fun observeNoteById(noteId: String): LiveData<Note>


    @Query("SELECT * FROM NOTES ORDER BY date DESC")
    fun getAllNotes(): Flow<List<Note>>


    @Query("SELECT * FROM NOTES WHERE isSynced = 0")
    suspend fun getAllUnsyncedNotes():List<Note>

}