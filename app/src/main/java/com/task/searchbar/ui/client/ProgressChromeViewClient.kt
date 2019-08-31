package com.task.searchbar.ui.client

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar

class ProgressChromeViewClient constructor(private val progressBar: ProgressBar) : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        progressBar.progress = newProgress
        super.onProgressChanged(view, newProgress)
    }
}