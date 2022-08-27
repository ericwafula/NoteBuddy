package tech.ericwathome.notebuddy.data.model

data class NoteBudyResponse(
    val currentPage: Int,
    val nextPage: Int,
    val notes: List<Note>,
    val previousPage: Any,
    val totalPages: Int
)