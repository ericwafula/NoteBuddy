package tech.ericwathome.notebuddy.data.remote.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.notebuddy.data.dto.NoteDto
import tech.ericwathome.notebuddy.data.model.Note
import tech.ericwathome.notebuddy.data.model.ResponseMessage

interface NoteRepository {
    suspend fun addNote(noteDto: NoteDto): ResponseMessage
    suspend fun allNotes(page: Int): Flow<PagingData<Note>>
    suspend fun findNote(id: Int): Note
    suspend fun updateNote(id: Int): ResponseMessage
    suspend fun deleteNote(id: Int): ResponseMessage
}