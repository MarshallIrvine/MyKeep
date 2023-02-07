package com.marshall.irvine.mykeep.data

import androidx.room.*
import com.marshall.irvine.mykeep.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Update
    suspend fun updateNote(noteToUpdate: Note)

    @Delete
    suspend fun deleteNote(noteToDelete: Note)

    @Insert
    suspend fun addNote(newNote: Note)
}