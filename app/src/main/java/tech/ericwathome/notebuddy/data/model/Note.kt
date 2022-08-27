package tech.ericwathome.notebuddy.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val dateOfCreation: String,
    val description: String,
    val title: String
)