package com.marshall.irvine.mykeep.data.repository

import com.marshall.irvine.mykeep.domain.model.Note
import com.marshall.irvine.mykeep.data.NoteDao
import com.marshall.irvine.mykeep.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao) : NoteRepository
{
    override fun getAllNotes() = noteDao.getAllNotes()
    override suspend fun create(note: Note) = noteDao.addNote(newNote = note)
    override suspend fun update(note: Note) = noteDao.updateNote(noteToUpdate = note)
    override suspend fun delete(note: Note) = noteDao.deleteNote(noteToDelete = note)
}