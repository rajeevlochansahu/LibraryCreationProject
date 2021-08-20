package com.rajeev.halodocapp.ui

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.rajeev.halodocapp.R


class WebViewActivity : AppCompatActivity() {
    private var browser: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        browser = findViewById<View>(R.id.webview) as WebView
        browser?.webViewClient = MyWebViewClient()
        browser?.settings?.javaScriptEnabled = true
        getIntentData()
    }

    private fun getIntentData() {
        val intent = intent
        if(intent.hasExtra("KEY")) {
            val key: String? = intent.getStringExtra("KEY")
            if(key!!.isNotEmpty()) {
                browser?.loadUrl(key)
            }
        }
    }

    private class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
    }
}