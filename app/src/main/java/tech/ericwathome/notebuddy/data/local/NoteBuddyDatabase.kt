package tech.ericwathome.notebuddy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.ericwathome.notebuddy.data.model.Note
import tech.ericwathome.notebuddy.util.DATABASE_VERSION

@Database(
    entities = [Note::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class NoteBuddyDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}