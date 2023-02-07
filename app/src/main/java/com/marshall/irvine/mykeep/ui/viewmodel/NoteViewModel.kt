package com.marshall.irvine.mykeep.ui.viewmodel


import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marshall.irvine.mykeep.data.repository.NoteRepositoryImpl
import com.marshall.irvine.mykeep.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepositoryImpl) :
    ViewModel() {

    val notesList = noteRepository.getAllNotes()

    fun addNewNote(note: Note) {
        viewModelScope.launch {
            noteRepository.create(note)
        }
    }

    fun delete(note: Note) {
        viewModelScope.launch {
            noteRepository.delete(note)
        }

    }
}