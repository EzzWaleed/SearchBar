package com.task.searchbar.ui

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.task.searchbar.data.resource.Resource
import com.task.searchbar.extensions.isHttpLink
import com.task.searchbar.extensions.loadUri
import com.task.searchbar.extensions.replaceSuggestions
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

    private lateinit var mainViewModel: MainViewModel

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.task.searchbar.R.layout.activity_main)
        setSupportActionBar(toolbar)

        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        initWebView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.task.searchbar.R.menu.menu_main, menu)
        menu?.let {
            initSearchView(it)
        }
        return true
    }

    private fun initSearchView(menu: Menu) {
        searchView = menu.findItem(com.task.searchbar.R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(searchListener)
        mainViewModel.queryState?.let { searchView.setQuery(it, true) }
        observeSuggestionsData()
    }

    private fun observeSuggestionsData() {
        mainViewModel.getSuggestionsLiveData().observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                }
                Resource.Status.SUCCESS -> {
                    searchView.replaceSuggestions(it.data)
                }
                Resource.Status.API_ERROR -> {
                    Log.d("suggests error", it.responseCode.toString())
                }
                Resource.Status.DOMAIN_ERROR -> {
                    Log.d("suggests error", it.throwable!!.localizedMessage)
                }
            }
        })
    }

    private val searchListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let {
                searchView.replaceSuggestions(ArrayList())
                val uri = mainViewModel.getUri(it)
                if (uri.isHttpLink())
                    webView.loadUri(uri)
                else
                    startActivity(Intent(ACTION_VIEW, uri))
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let { if (it.length > 1) mainViewModel.getSuggestions(newText) }
            return false
        }
    }

    private fun initWebView() {
        webView.webChromeClient = ProgressChromeViewClient(progressBar)
        webView.webViewClient = ProgressWebViewClient(progressBar)
    }

    override fun onBackPressed() {
        if (!searchView.isIconified)
            searchView.isIconified = true
        else if (webView.canGoBack())
            webView.goBack()
        else
            super.onBackPressed()
    }
}
