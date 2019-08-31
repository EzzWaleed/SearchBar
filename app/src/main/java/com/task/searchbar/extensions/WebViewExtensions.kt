package com.task.searchbar.extensions

import android.net.Uri
import android.webkit.WebView

fun WebView.loadUri(uri: Uri) {
    val stringBuilder = StringBuilder(uri.scheme)
    stringBuilder
        .append("://")
        .append(uri.host)

    uri.path?.let { stringBuilder.append(it) }
    uri.query?.let {
        stringBuilder.append("?")
        stringBuilder.append(it) }

    loadUrl(stringBuilder.toString())
}