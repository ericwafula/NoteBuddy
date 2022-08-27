package tech.ericwathome.notebuddy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import tech.ericwathome.notebuddy.data.dto.NoteDto
import tech.ericwathome.notebuddy.data.model.Note
import tech.ericwathome.notebuddy.data.model.ResponseMessage
import tech.ericwathome.notebuddy.data.remote.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NotesFragmentViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _notes = MutableStateFlow<Flow<PagingData<Note>>?>(null)
    val notes = _notes.asStateFlow()
    private val _note = MutableStateFlow<Note?>(null)
    val note = _note.asStateFlow()
    private val _message = MutableStateFlow<ResponseMessage?>(null)
    val message = _message.asStateFlow()

    fun allNotes() {
        viewModelScope.launch {
            _notes.value = repository.allNotes()
                .flowOn(Dispatchers.IO)
                .cachedIn(viewModelScope)
        }
    }

    fun findNote(id: Int) {
        viewModelScope.launch {
            _note.value = repository.findNote(id)
        }
    }

    fun updateNote(id: Int, note: NoteDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _message.value = repository.updateNote(id, note)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _message.value = repository.deleteNote(id)
        }
    }
}