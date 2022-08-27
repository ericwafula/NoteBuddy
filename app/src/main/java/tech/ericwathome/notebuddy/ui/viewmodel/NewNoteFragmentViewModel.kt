package tech.ericwathome.notebuddy.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tech.ericwathome.notebuddy.data.dto.NoteDto
import tech.ericwathome.notebuddy.data.remote.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NewNoteFragmentViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private val _message = MutableStateFlow<String?>(null)
    val message = _message.asStateFlow()

    fun addNote(noteDto: NoteDto) {
        viewModelScope.launch {
            val response = repository.addNote(noteDto)
            _message.value = response.message
        }
    }

}