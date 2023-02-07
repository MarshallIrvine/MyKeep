package com.marshall.irvine.mykeep.di

import android.content.Context
import com.marshall.irvine.mykeep.data.NoteDao
import com.marshall.irvine.mykeep.data.NoteDatabase
import com.marshall.irvine.mykeep.data.repository.NoteRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteAppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return NoteDatabase.getInstance(context = context)
    }

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao {
        return noteDatabase.noteDao()
    }

    @Provides
    fun provideNoteRepository(noteDao: NoteDao): NoteRepositoryImpl {
        return NoteRepositoryImpl(noteDao = noteDao)
    }
}