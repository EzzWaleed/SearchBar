package com.task.searchbar.extensions

import android.net.Uri

fun Uri.isHttpLink(): Boolean {
    scheme?.let {
        return (it == "http" || it == "https")
    }
    return false
}