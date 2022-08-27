package tech.ericwathome.notebuddy.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import tech.ericwathome.notebuddy.data.model.Note
import tech.ericwathome.notebuddy.data.remote.NoteBudyApiService
import tech.ericwathome.notebuddy.util.STARTING_PAGE
import java.lang.Exception

class NoteBuddyPagingSource(private val apiService: NoteBudyApiService) : PagingSource<Int, Note>() {
    override val keyReuseSupported: Boolean = true

    override fun getRefreshKey(state: PagingState<Int, Note>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Note> {
        val page = params.key ?: STARTING_PAGE
        val noteResponse = apiService.allNotes(page)

        return try {
            LoadResult.Page(
                data = noteResponse.notes,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = noteResponse.nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}