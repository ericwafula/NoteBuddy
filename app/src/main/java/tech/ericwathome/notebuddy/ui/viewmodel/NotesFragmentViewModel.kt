package tech.ericwathome.notebuddy.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.ericwathome.notebuddy.data.remote.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NotesFragmentViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
}