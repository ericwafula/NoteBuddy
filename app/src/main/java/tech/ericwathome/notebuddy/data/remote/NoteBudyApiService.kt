package tech.ericwathome.notebuddy.data.remote

import retrofit2.http.*
import tech.ericwathome.notebuddy.data.model.Note

interface NoteBudyApiService {
    @POST("notes/new")
    suspend fun addNote(
        @Body note: Note
    )

    @GET("notes")
    suspend fun allNotes(
        @Query("page") page: Int
    )

    @GET("notes/find")
    suspend fun findNote(
        @Query("id") id: Int
    )

    @PATCH("notes/update")
    suspend fun updateNote(
        @Query("id") id: Int
    )

    @DELETE("notes/delete")
    suspend fun deleteNote(
        @Query("id") id: Int
    )
}