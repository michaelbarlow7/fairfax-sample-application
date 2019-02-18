package com.michaelbarlow.fairfaxapplication.articleview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import com.michaelbarlow.fairfaxapplication.R
import kotlinx.android.synthetic.main.activity_article_view.*

/**
 * Basic activity that shows the url passed into extras in a webview
 */
class ArticleViewActivity : AppCompatActivity(){

    companion object {
        const val ARTICLE_URL_EXTRA = "ARTICLE_URL_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_article_view)

        articleWebView.settings.javaScriptEnabled = true
        articleWebView.webViewClient = WebViewClient()
        articleWebView.loadUrl(intent.getStringExtra(ARTICLE_URL_EXTRA))
    }
}