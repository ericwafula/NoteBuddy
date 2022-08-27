package tech.ericwathome.notebuddy.data.remote

import retrofit2.http.*
import tech.ericwathome.notebuddy.data.dto.NoteDto
import tech.ericwathome.notebuddy.data.model.Note
import tech.ericwathome.notebuddy.data.model.NoteBudyResponse
import tech.ericwathome.notebuddy.data.model.ResponseMessage
import java.util.concurrent.Flow

interface NoteBudyApiService {
    @POST("notes/new")
    suspend fun addNote(
        @Body note: NoteDto
    ): ResponseMessage

    @GET("notes")
    suspend fun allNotes(
        @Query("page") page: Int
    ): NoteBudyResponse

    @GET("notes/find")
    suspend fun findNote(
        @Query("id") id: Int
    ): Note

    @PATCH("notes/update")
    suspend fun updateNote(
        @Query("id") id: Int
    ): ResponseMessage

    @DELETE("notes/delete")
    suspend fun deleteNote(
        @Query("id") id: Int
    ): ResponseMessage
}