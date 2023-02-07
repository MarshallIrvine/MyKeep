package com.marshall.irvine.mykeep.domain.repository

import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<com.marshall.irvine.mykeep.domain.model.Note>>
    suspend fun create(note: com.marshall.irvine.mykeep.domain.model.Note)
    suspend fun update(note: com.marshall.irvine.mykeep.domain.model.Note)
    suspend fun delete(note: com.marshall.irvine.mykeep.domain.model.Note)
}