package com.task.searchbar.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.task.searchbar.R
import com.task.searchbar.extensions.isHttpLink
import com.task.searchbar.extensions.loadUri
import com.task.searchbar.presentation.viewmodel.MainViewModel
import com.task.searchbar.presentation.viewmodel.viewmodel_factory.ViewModelFactory
import com.task.searchbar.ui.client.ProgressChromeViewClient
import com.task.searchbar.ui.client.ProgressWebViewClient
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var mainViewModel: MainViewModel

    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        initWebView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(searchListener)
        return true
    }

    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                val uri = mainViewModel.getUri(it)
                if (uri.isHttpLink())
                    webView.loadUri(uri)
                else
                    startActivity(Intent(ACTION_VIEW, uri))
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    private fun initWebView() {
        webView.webChromeClient = ProgressChromeViewClient(progressBar)
        webView.webViewClient = ProgressWebViewClient(progressBar)
    }

    override fun onBackPressed() {
        if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }
}
