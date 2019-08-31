package com.task.searchbar.extensions

import android.R
import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView

import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter

private fun SearchView.initSuggestionAdapterAdapter(){
    val columnNames = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
    val viewIds = intArrayOf(R.id.text1)
    suggestionsAdapter = SimpleCursorAdapter(
        context,
        R.layout.simple_list_item_2,
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
