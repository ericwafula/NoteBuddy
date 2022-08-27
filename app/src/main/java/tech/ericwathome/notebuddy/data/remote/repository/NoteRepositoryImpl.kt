package tech.ericwathome.notebuddy.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import tech.ericwathome.notebuddy.data.dto.NoteDto
import tech.ericwathome.notebuddy.data.local.NoteDao
import tech.ericwathome.notebuddy.data.model.Note
import tech.ericwathome.notebuddy.data.model.ResponseMessage
import tech.ericwathome.notebuddy.data.remote.NoteBudyApiService
import tech.ericwathome.notebuddy.data.remote.paging.NoteBuddyPagingSource
import tech.ericwathome.notebuddy.util.PAGE_LOAD_SIZE
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val apiService: NoteBudyApiService, val dao: NoteDao) : NoteRepository {
    override suspend fun addNote(noteDto: NoteDto): ResponseMessage {
        return apiService.addNote(noteDto)
    }

    override suspend fun allNotes(page: Int): Flow<PagingData<Note>> {
        val pagingConfig = PagingConfig(
            pageSize = PAGE_LOAD_SIZE,
            enablePlaceholders = false
        )

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { NoteBuddyPagingSource(apiService) }
        ).flow
    }

    override suspend fun findNote(id: Int): Note {
        return apiService.findNote(id)
    }

    override suspend fun updateNote(id: Int): ResponseMessage {
        return apiService.deleteNote(id)
    }

    override suspend fun deleteNote(id: Int): ResponseMessage {
        return apiService.deleteNote(id)
    }
}