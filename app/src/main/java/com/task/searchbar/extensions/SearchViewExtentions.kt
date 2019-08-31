import android.app.SearchManager
import android.database.MatrixCursor
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import com.task.searchbar.R

private fun SearchView.initSuggestionAdapterAdapter(){
    val columnNames = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
    val viewIds = intArrayOf(R.id.tv)
    suggestionsAdapter = SimpleCursorAdapter(
        context,
        R.layout.list_item_drop_down,
        null,
        columnNames,
        viewIds,
        CursorAdapter.IGNORE_ITEM_VIEW_TYPE)

    setOnSuggestionListener(object : SearchView.OnSuggestionListener {
        override fun onSuggestionSelect(position: Int): Boolean {
            return false
        }

        override fun onSuggestionClick(position: Int): Boolean {
            val cursor = suggestionsAdapter.cursor
            cursor.moveToPosition(position)
            val query = cursor.getString(1)
            setQuery(query, true)
            return true
        }
    })
}

fun SearchView.replaceSuggestions(suggestions: List<String>?){
    suggestions?.let {
        if (suggestionsAdapter == null)
            initSuggestionAdapterAdapter()
        else
            suggestionsAdapter.cursor.close()

        val menuCols = arrayOf(
            "_id",
            SearchManager.SUGGEST_COLUMN_TEXT_1
        )

        val cursor = MatrixCursor(menuCols)

        for (i in it.indices) {
            cursor.addRow(arrayOf(i, it[i]))
        }

        suggestionsAdapter.swapCursor(cursor)

    }
}
